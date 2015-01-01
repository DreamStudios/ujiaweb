DELIMITER $$

USE `ujiajoke`$$

DROP TRIGGER /*!50032 IF EXISTS */ `joke_AUPT`$$

CREATE
    /*!50017 DEFINER = 'root'@'localhost' */
    TRIGGER `joke_AUPT` AFTER UPDATE ON `joke` 
    FOR EACH ROW 
BEGIN

   -- 笑话状态(0:审核未通过 1:待审核 2:审核通过)
   IF (new.status!=old.status&&new.status=2) THEN -- 笑话审核通过后，奖励0.1元+50经验
      UPDATE ujiajoke.`userinfo` SET integral = integral + 1,experience = experience + 50 WHERE uid = new.uid;
   END IF;
END;
$$

DELIMITER ;