/* just some tasty test data during development */

insert into food_choice(id) values (1);
insert into food_choice(id) values (2);
insert into food_choice(id) values (3);
insert into food_choice(id) values (4);
insert into food_choice(id) values (5);
insert into food_choice(id) values (6);
insert into food_choice(id) values (7);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(1,1,'eggs dozen',2,1,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(2,2,'milk',2,1,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(3,3,'chicken thighs 5lb',2,1,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(4,3,'chicken legs 5lb',2,1,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(5,3,'hamburger 5lb',2,1,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(6,4,'bread rolls',1,0,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(7,4,'stuffing',0,1,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(8,5,'frozen barley',1,1,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(9,6,'frozen shrimp',1,1,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(10,7,'dont see me',2,1,0);