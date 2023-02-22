# NotebookApp

Spring project on Java using basic stack of technologies: 
*Authentication (Security) added *

Spring Framework(Boot, Security, JPA), Apache Maven, MySQL and Thymeleaf + bootstrap on view side.

It's a simple CRUD service for managing custom notes.
You can view, add, update, delete, filter and sort your notes.

Below are some screenshots with an explanation.

## 'Main' page
![image](https://user-images.githubusercontent.com/75338292/208548903-93fc8aef-654c-4899-a842-238e71bee3be.png)

That's the first page that will appear on user's device. It represents list of notes with creation (or change) date and actual status.
It is also provides all of the controller elements:
  - 'Delete' button
  - 'Edit' button
  - 'New note' button
  - 'Status changer' button
  - 'Sort by date' arrows
  - 'filter by status' dropdown menu
  
![image](https://user-images.githubusercontent.com/75338292/208550009-234a4ef3-8888-492d-9019-82d44aa62dc8.png)


## 'Add new note' page
![image](https://user-images.githubusercontent.com/75338292/208550359-9da0c78f-3ca7-4692-973d-cc0a41c53ba3.png)

After clicking on 'New note' button user wil be redirected to the new page wich is pretty simple, it's only have simple input form for the note.
And after pressing 'Submit' button server will automaticly add current date, set 'done status' to false and save note to the repository with new ID.

## 'Edit note' page
![image](https://user-images.githubusercontent.com/75338292/208551137-64a41c80-4aa1-4c53-8da8-8b9c16070199.png)

From the view side the 'Edit' page is simillar to the 'Add new note' page, the only difference is in the text filed. Server will pass the text value from selected note to the input form, so user can see what he currently has and do some small or big changes. In this case after pressing the 'submit' button server will only update text of the note, date and will save the changes to the repository.

