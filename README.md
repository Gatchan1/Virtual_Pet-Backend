Hi there!
This is a full stack project, here is the frontend repo (built with React): https://github.com/Gatchan1/Virtual_Pet-Frontend

This backend is built using Spring Web MVC and it's main learning goal was the **authentication** with role based authorization.
The database used is **MongoDB**.

The idea of the app is that the user would create a user account, and once logged in they can create their own **virtual pets**, 
which they'll have to take care of through feeding and playing with them, and also one can change the pet's accessories and/or change to a different location.

![Captura de pantalla 2024-12-10 203802](https://github.com/user-attachments/assets/230641cf-50e9-40ba-bd98-859db7fafc35)
This is how the frontend looks, when you check in a specific pet.

This would be the database schema diagram I built:\
<img src="https://github.com/user-attachments/assets/ed79d911-794d-4f9b-82a9-468dbac42a6d" alt="virtual_pet-mongo_diagram" width="700">\
When a pet is created, it comes with a "unique" personality: random preferences regarding accessories and locations.
Also their level of happiness and energy decreases automatically as time passes by, but when sleeping they regain energy.

---
\
This was one of the two final projects made for my Java & Spring Framework bootcamp (@It Academy, Barcelona, 2024).  \
I had a lot of fun building it! ✨
