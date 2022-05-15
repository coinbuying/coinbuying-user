DROP TABLE IF EXISTS users; -- board 테이블이 존재할 경우 DROP

CREATE TABLE IF NOT EXISTS users ( -- board 테이블이 없을 경우 테이블 생성
    user_id INT(20) AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '유저',
    user_type VARCHAR(10) NOT NULL COMMENT '유저타입(일반회원, 운영자)',
    name VARCHAR(50) NOT NULL COMMENT '이름',
    email VARCHAR(50) NOT NULL COMMENT 'email',
    password VARCHAR(100) NOT NULL COMMENT 'password',
    create_dt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    update_dt DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);