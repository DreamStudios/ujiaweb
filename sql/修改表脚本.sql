-- 广告表
ALTER TABLE ujiajoke.`ad` ADD INDEX ad_status (ad_status);
ALTER TABLE ujiajoke.`ad` ADD INDEX TYPE (TYPE);
-- 普通笑话表
ALTER TABLE ujiajoke.`joke` ADD INDEX STATUS (STATUS);
ALTER TABLE ujiajoke.`joke` ADD INDEX style (style);
ALTER TABLE ujiajoke.`joke` ADD INDEX TYPE (TYPE);
ALTER TABLE ujiajoke.`joke` ADD INDEX uid (uid);
ALTER TABLE ujiajoke.`joke` ADD INDEX tid (tid);
-- 普通笑话评论
ALTER TABLE ujiajoke.`jokecomment` ADD INDEX jid (jid);
-- 会员笑话表
ALTER TABLE ujiajoke.`vipjoke` ADD INDEX STATUS (STATUS);
ALTER TABLE ujiajoke.`vipjoke` ADD INDEX style (style);
ALTER TABLE ujiajoke.`vipjoke` ADD INDEX uid (uid);
-- 普通笑话评论
ALTER TABLE ujiajoke.`vipjokecomment` ADD INDEX jid (jid);