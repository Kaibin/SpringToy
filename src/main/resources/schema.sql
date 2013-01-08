CREATE DATABASE demo CHARACTER SET utf8 COLLATE utf8_bin;
drop table if exists article;
drop table if exists category;

create table category(
	id bigint auto_increment,
	name varchar(32) not null,
	primary key(id)
)engine=InnoDB;


create table article(
    id bigint auto_increment,
    title varchar(128),
    date timestamp not null default CURRENT_TIMESTAMP,
    description varchar(255),
    author varchar(16),
    content varchar(255),
    link varchar(128),
    attachment varchar(128),
    category_id bigint not null,
    primary key(id)	    
)engine=InnoDB;

insert into category(name) values("娱乐");
insert into category(name) values("体育");

insert into article(title,description,author,content,link,category_id)  values("成龙","电影","info@herosordinaires.com (Oleiade)", "http://www.idaocao.com/yule_html/yl_chinanews/2008422122213_10260.shtml","http://www.idaocao.com/yule_html/yl_chinanews/2008422122213_10260.shtml", 1);
insert into article(title,description,author,content,link,category_id)  values("周杰伦","音乐","info@herosordinaires.com (Oleiade)", "http://www.idaocao.com/yule_html/yl_chinanews/2008422122213_10260.shtml","http://www.idaocao.com/yule_html/yl_chinanews/2008422122213_10260.shtml", 1);
insert into article(title,description,author,content,link,category_id)  values("篮球","詹姆斯","info@herosordinaires.com (Oleiade)", "http://www.idaocao.com/yule_html/yl_chinanews/2008422122213_10260.shtml","http://www.idaocao.com/yule_html/yl_chinanews/2008422122213_10260.shtml", 2);
insert into article(title,description,author,content,link,category_id)  values("足球","罗纳尔多","info@herosordinaires.com (Oleiade)", "http://www.idaocao.com/yule_html/yl_chinanews/2008422122213_10260.shtml","http://www.idaocao.com/yule_html/yl_chinanews/2008422122213_10260.shtml", 2);






