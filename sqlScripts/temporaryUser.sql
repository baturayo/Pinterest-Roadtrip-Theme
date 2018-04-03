/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  cekef
 * Created: Mar 17, 2018
 */

delete from loggedintimestamps where user_id = 1;
delete from user where id = 1;
insert into user values (0,"a","a","a",1,"a","a","a");