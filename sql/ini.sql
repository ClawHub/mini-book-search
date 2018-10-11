drop table if exists t_book_info;

/*==============================================================*/
/* Table: t_book_info                                           */
/*==============================================================*/
create table t_book_info
(
   id                   varchar(256) not null comment '书籍id',
   name                 varchar(128) comment '书名',
   auther               varchar(64) comment '作者',
   site                 varchar(1) comment '站点 0:女 1:男  ',
   classify             varchar(2) comment '分类 0：玄幻 1：奇幻 2：武侠 3：仙侠 4：都市 。。。。',
   state                varchar(1) comment '状态 0：连载 1：完本',
   attribute            varchar(1) comment '属性 0：免费 1:vip',
   number               int comment '字数',
   remark               varchar(512) comment '简介',
   pic_url              varchar(255) comment '图片链接',
   primary key (id)
);

alter table t_book_info comment '书籍信息';
drop table if exists t_book_source;

/*==============================================================*/
/* Table: t_book_source                                         */
/*==============================================================*/
create table t_book_source
(
   source_id            varchar(256) not null comment '书籍源id',
   book_id              varchar(256) comment '书籍id',
   url                  varchar(256) comment '小说链接',
   update_time          bigint comment '更新时间',
   catalog_url          varchar(256) comment '目录链接',
   web_site             varchar(256) comment 'web站点',
   primary key (source_id)
);

alter table t_book_source comment '书籍来源表';
drop table if exists t_chapter;

/*==============================================================*/
/* Table: t_chapter                                             */
/*==============================================================*/
create table t_chapter
(
   id                   varchar(256) not null comment '章节id',
   source_id            varchar(256) not null comment '书籍源id',
   name                 varchar(256) comment '章节名称',
   url                  varchar(256) comment '章节链接',
   date_time            bigint comment '首发时间',
   sort                 int comment '序列',
   number               int comment '章节字数',
   primary key (id)
);

alter table t_chapter comment '章节表';
drop table if exists t_recommend;

/*==============================================================*/
/* Table: t_recommend                                           */
/*==============================================================*/
create table t_recommend
(
   id                   varchar(256) not null comment 'id',
   book                 varchar(128) comment '书名',
   create_time          bigint comment '创建时间',
   primary key (id)
);

alter table t_recommend comment '推荐表';