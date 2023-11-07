/* just some tasty test data during development */

insert into food_choice(id) values (1);
insert into food_choice(id) values (2);
insert into food_choice(id) values (3);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(1,1,'chicken thighs',2,1,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(2,1,'chicken legs',2,1,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(3,2,'bread rolls',1,0,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(4,2,'stuffing',0,1,10);
insert into food_item(id, food_choice_id, name, large_family_quantity, small_family_quantity, quantity_available)
values(5,3,'dont see me',2,1,0);