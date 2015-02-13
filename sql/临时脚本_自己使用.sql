-- 将锁屏广告列表导入到有家笑话广告列表
INSERT INTO `ujiajoke`.`ad` (`ad_photo`,`ad_status`,`ad_title`,`ad_url`,`create_time`,`type`,`update_time`,`advid`,`content`,`weight`,`style`,`down`,`name`,`size`,`screenshot1`,`screenshot2`,`flag`,`package_name`)
SELECT a.`source_icon`,2,a.`app_name`,a.`source_icon`,NOW(),1,NOW(),1,a.`push_intro`,1,0,ROUND(ROUND(RAND(),5)*100,2),a.`app_name`,a.`app_size`,'','',1,a.`package_name` FROM excavatordb.`ad` a;


