
drop database audiro;
-- 사용자 생성, %는 외부 접근 허용
create user 'fann'@'%' identified by 'fann';
-- 권한 부여 *.*는 모든 권한 
GRANT ALL PRIVILEGES ON *.* TO 'fann'@'%';
-- database security 생성
create database audirodb;
use audirodb;