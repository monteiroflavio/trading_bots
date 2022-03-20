drop schema if exists `trading_bots`;
create schema if not exists `trading_bots`;
use `trading_bots`;

drop table if exists `trading_bots`.`bots`;
create table if not exists `trading_bots`.`bots`
(
    `id`             int         not null auto_increment,
    `name`           varchar(50) not null,
    `interval`       int         not null,
    `order_quantity` int         not null,
    `creation_date`  timestamp        default current_timestamp,
    `update_date`    timestamp   null default null on update current_timestamp,
    PRIMARY KEY (`id`)
)
    engine = InnoDB;

drop table if exists `trading_bots`.`moving_average_configs`;
create table if not exists `trading_bots`.`moving_average_configs`
(
    `id`            int                                        not null auto_increment,
    `periods`       int                                        not null,
    `type`          enum ('SHORT', 'LONG')                     not null,
    `operation`     enum ('EXPONENTIAL', 'SIMPLE', 'WEIGHTED') not null default 'SIMPLE',
    `reference`     enum ('HIGH', 'LOW', 'OPEN', 'CLOSE')      not null,
    `bot_id`        int                                        not null,
    `creation_date` timestamp                                           default current_timestamp,
    `update_date`   timestamp                                  null     default null on update current_timestamp,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_moving_average_configs_bots`
        FOREIGN KEY (`bot_id`)
            REFERENCES `trading_bots`.`bots` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `unique_moving_average_configs_type_bot_id` UNIQUE (`type`, `bot_id`)
)
    engine = InnoDB;

drop table if exists `trading_bots`.`currencies`;
create table if not exists `trading_bots`.`currencies`
(
    `id`       int         not null auto_increment,
    `currency` varchar(50) not null,
    PRIMARY KEY (`id`)
)
    engine = InnoDB;

drop table if exists `trading_bots`.`bots_currencies`;
create table if not exists `trading_bots`.`bots_currencies`
(
    `id`            int                          not null auto_increment,
    `bot_id`        int                          not null,
    `currency_id`   int                          not null,
    `creation_date` timestamp                             default current_timestamp,
    `update_date`   timestamp                    null     default null on update current_timestamp,
    `status`        enum ('ENABLED', 'DISABLED') not null default 'DISABLED',
    PRIMARY KEY (`id`),
    CONSTRAINT `unique_bots_currencies_bot_currency` UNIQUE (`bot_id`, `currency_id`),
    CONSTRAINT `fk_bots_currencies_bots`
        FOREIGN KEY (`bot_id`)
            REFERENCES `trading_bots`.`bots` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_bots_currencies_currencies`
        FOREIGN KEY (`currency_id`)
            REFERENCES `trading_bots`.`currencies` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    engine = InnoDB;

drop table if exists `trading_bots`.`currencies_entries`;
create table if not exists `trading_bots`.`currencies_entries`
(
    `id`             int            not null auto_increment,
    `currency_id`    int            not null,
    `value`          decimal(10, 2) not null,
    `entry_datetime` timestamp      not null,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_currencies_entries_currencies`
        FOREIGN KEY (`currency_id`)
            REFERENCES `trading_bots`.`currencies` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    engine = InnoDB;

drop table if exists `trading_bots`.`currencies_periods`;
create table if not exists `trading_bots`.`currencies_periods`
(
    `id`                      int            not null auto_increment,
    `sequence`                int            not null,
    `currency_id`             int            not null,
    `currency_entry_start_id` int            not null,
    `currency_entry_end_id`   int            not null,
    `open_value`              decimal(10, 2) not null,
    `close_value`             decimal(10, 2) not null,
    `high_value`              decimal(10, 2) not null,
    `low_value`               decimal(10, 2) not null,
    `interval`                int            not null,
    `creation_date`           timestamp           default current_timestamp,
    `update_date`             timestamp      null default null on update current_timestamp,
    PRIMARY KEY (`id`),
    CONSTRAINT `unique_currencies_periods_currencies_start_end_sequence_interval`
        UNIQUE (`currency_entry_start_id`, `currency_entry_end_id`, `sequence`, `interval`),
    CONSTRAINT `fk_currencies_periods_currencies_entries_start`
        FOREIGN KEY (`currency_entry_start_id`)
            REFERENCES `trading_bots`.`currencies_entries` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_currencies_periods_currencies_entries_end`
        FOREIGN KEY (`currency_entry_end_id`)
            REFERENCES `trading_bots`.`currencies_entries` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_currencies_periods_currencies`
        FOREIGN KEY (`currency_id`)
            REFERENCES `trading_bots`.`currencies` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    engine = InnoDB;

drop table if exists `trading_bots`.`moving_averages`;
create table if not exists `trading_bots`.`moving_averages`
(
    `id`                 int                                        not null auto_increment,
    `currency_period_id` int                                        not null,
    `value`              decimal(10, 2)                             not null,
    `operation`          enum ('EXPONENTIAL', 'SIMPLE', 'WEIGHTED') not null,
    `reference`          enum ('HIGH', 'LOW', 'OPEN', 'CLOSE')      not null,
    `periods`            int                                        not null,
    PRIMARY KEY (`id`),
    CONSTRAINT `unique_moving_averages_cp_periods_operation_reference`
        UNIQUE (`currency_period_id`, `operation`, `periods`, `reference`),
    CONSTRAINT `fk_moving_averages_currencies_periods`
        FOREIGN KEY (`currency_period_id`)
            REFERENCES `trading_bots`.`currencies_periods` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    engine = InnoDB;

drop table if exists `trading_bots`.`orders`;
create table if not exists `trading_bots`.`moving_averages`
(
    `id`            int                       not null auto_increment,
    `bot_id`        int                       not null,
    `value`         decimal(10, 2)            not null,
    `type`          enum ('PURCHASE', 'SALE') not null,
    `creation_date` timestamp default current_timestamp,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_orders_bots`
        FOREIGN KEY (`bot_id`)
            REFERENCES `trading_bots`.`bots` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    engine = InnoDB;

insert into `trading_bots`.`currencies` (id, currency)
values (177, 'BTC_ARDR'),
       (253, 'BTC_ATOM'),
       (210, 'BTC_BAT'),
       (189, 'BTC_BCH'),
       (236, 'BTC_BCHABC'),
       (238, 'BTC_BCHSV'),
       (7, 'BTC_BCN'),
       (232, 'BTC_BNT'),
       (14, 'BTC_BTS'),
       (15, 'BTC_BURST'),
       (20, 'BTC_CLAM'),
       (194, 'BTC_CVC'),
       (24, 'BTC_DASH'),
       (162, 'BTC_DCR'),
       (25, 'BTC_DGB'),
       (27, 'BTC_DOGE'),
       (201, 'BTC_EOS'),
       (171, 'BTC_ETC'),
       (148, 'BTC_ETH'),
       (155, 'BTC_FCT'),
       (246, 'BTC_FOAM'),
       (38, 'BTC_GAME'),
       (198, 'BTC_GAS'),
       (185, 'BTC_GNT'),
       (251, 'BTC_GRIN'),
       (43, 'BTC_HUC'),
       (207, 'BTC_KNC'),
       (167, 'BTC_LBC'),
       (213, 'BTC_LOOM'),
       (250, 'BTC_LPT'),
       (163, 'BTC_LSK'),
       (50, 'BTC_LTC'),
       (51, 'BTC_MAID'),
       (229, 'BTC_MANA'),
       (61, 'BTC_NAV'),
       (64, 'BTC_NMC'),
       (248, 'BTC_NMR'),
       (69, 'BTC_NXT'),
       (196, 'BTC_OMG'),
       (58, 'BTC_OMNI'),
       (184, 'BTC_PASC'),
       (249, 'BTC_POLY'),
       (75, 'BTC_PPC'),
       (221, 'BTC_QTUM'),
       (174, 'BTC_REP'),
       (170, 'BTC_SBD'),
       (150, 'BTC_SC'),
       (204, 'BTC_SNT'),
       (168, 'BTC_STEEM'),
       (200, 'BTC_STORJ'),
       (89, 'BTC_STR'),
       (182, 'BTC_STRAT'),
       (92, 'BTC_SYS'),
       (97, 'BTC_VIA'),
       (100, 'BTC_VTC'),
       (108, 'BTC_XCP'),
       (112, 'BTC_XEM'),
       (114, 'BTC_XMR'),
       (116, 'BTC_XPM'),
       (117, 'BTC_XRP'),
       (178, 'BTC_ZEC'),
       (192, 'BTC_ZRX'),
       (211, 'ETH_BAT'),
       (190, 'ETH_BCH'),
       (233, 'ETH_BNT'),
       (195, 'ETH_CVC'),
       (202, 'ETH_EOS'),
       (172, 'ETH_ETC'),
       (199, 'ETH_GAS'),
       (186, 'ETH_GNT'),
       (208, 'ETH_KNC'),
       (214, 'ETH_LOOM'),
       (166, 'ETH_LSK'),
       (230, 'ETH_MANA'),
       (197, 'ETH_OMG'),
       (222, 'ETH_QTUM'),
       (176, 'ETH_REP'),
       (205, 'ETH_SNT'),
       (169, 'ETH_STEEM'),
       (179, 'ETH_ZEC'),
       (193, 'ETH_ZRX'),
       (254, 'USDC_ATOM'),
       (235, 'USDC_BCH'),
       (237, 'USDC_BCHABC'),
       (239, 'USDC_BCHSV'),
       (224, 'USDC_BTC'),
       (243, 'USDC_DOGE'),
       (225, 'USDC_ETH'),
       (247, 'USDC_FOAM'),
       (252, 'USDC_GRIN'),
       (244, 'USDC_LTC'),
       (242, 'USDC_STR'),
       (226, 'USDC_USDT'),
       (241, 'USDC_XMR'),
       (240, 'USDC_XRP'),
       (245, 'USDC_ZEC'),
       (256, 'USDC_DASH'),
       (257, 'USDC_EOS'),
       (258, 'USDC_ETC'),
       (255, 'USDT_ATOM'),
       (212, 'USDT_BAT'),
       (191, 'USDT_BCH'),
       (234, 'USDT_BNT'),
       (121, 'USDT_BTC'),
       (122, 'USDT_DASH'),
       (216, 'USDT_DOGE'),
       (203, 'USDT_EOS'),
       (173, 'USDT_ETC'),
       (149, 'USDT_ETH'),
       (217, 'USDT_GNT'),
       (209, 'USDT_KNC'),
       (215, 'USDT_LOOM'),
       (218, 'USDT_LSK'),
       (123, 'USDT_LTC'),
       (231, 'USDT_MANA'),
       (124, 'USDT_NXT'),
       (223, 'USDT_QTUM'),
       (175, 'USDT_REP'),
       (219, 'USDT_SC'),
       (206, 'USDT_SNT'),
       (125, 'USDT_STR'),
       (126, 'USDT_XMR'),
       (127, 'USDT_XRP'),
       (180, 'USDT_ZEC'),
       (220, 'USDT_ZRX'),
       (259, 'USDT_BCHSV'),
       (260, 'USDT_BCHABC'),
       (261, 'USDT_GRIN'),
       (262, 'USDT_DGB'),
       (129, 'XMR_BCN'),
       (132, 'XMR_DASH'),
       (137, 'XMR_LTC'),
       (138, 'XMR_MAID'),
       (140, 'XMR_NXT'),
       (181, 'XMR_ZEC');

commit;