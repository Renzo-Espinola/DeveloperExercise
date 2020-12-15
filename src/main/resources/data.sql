DROP TABLE IF EXISTS trello_assistant;

INSERT INTO Category (category) VALUES
('Maintenance'),
('Research'),
('Test');

INSERT INTO type_task (default_tag,type,has_Title,has_Description,has_Category) VALUES
('ISSUE','ISSUE',true,true,false),
('BUG','bug',false,true,false),
('TASK','TASK',true,false,true);






