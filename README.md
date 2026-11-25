Sistema de Clínica Veterinaria
Aplicación de escritorio en Java (Swing) para gestionar la operación una clínica veterinaria: registro de propietarios y mascotas, agendamiento de citas, consultas médicas, historial clínico y facturación.
En base a la materia de Programación Orientada a Objetos (POO) , Surge de un caso de negocio donde se generan pérdida de información, errores de tarifa, etc... El sistema realiza las operaciones sin una base de datos, es decir cada que se cierre el programa la información se pierde.
Tabla de contenido
1.	Características:
La interfaz esta organizada en 5 pestañas
•	Primera pestaña: Registrar propietarios, mascotas (convencionales y exóticas) y colaboradores (recepcionistas y veterinarios).
•	Segunda pestaña: Permite agendar citas y atenderlas: diagnóstico, notas médicas, aplicación de vacunas o sedación.
•	Tercera Pestaña: Consulta el historial médico completo de cada mascota
•	Cuarta Pestaña: Generar el cobro de las consultas pendientes e imprimir el comprobante de pago.
•	Quinta Pestaña: Agregar vacunas al sistema, modificar elementos como la tarifa base y el costo de sedación, así mismo el registro de los colaboradores.

2.	Reglas de negocio

•	Tarifa base: $50.000 (configurable).
•	Exóticas: +25 % y pueden requerir sedación ($30.000).
•	Vacunas: solo para convencionales.
•	Más de 2 mascotas: 10 % de descuento.
•	Cada cita se atiende una vez y cada consulta se cobra una vez.

Validaciones principales: documento, nombre y email obligatorios; teléfono de exactamente 10 dígitos; sueldo no negativo; edad y peso de la mascota mayores a 0; raza obligatoria en mascotas convencionales; documentos de propietarios, colaboradores y IDS de mascotas no repetidos; diagnóstico y notas médicas obligatorios.

4.	Casos de uso
Actor
Recepcionista	Registrar propietario · Registrar mascota · Registrar colaborador · Agendar cita · Cobrar consulta y emitir comprobante · Configurar tarifa, sedación y vacunas
Veterinario	Atender cita · Registrar diagnóstico y notas médicas · Aplicar vacunas · Registrar sedación · Consultar historial clínico

5.	Arquitectura
El proyecto sigue el patrón MVC (Modelo – Vista – Controlador):
•	Modelo (veterinariaPOO.modelo): clases del dominio y lógica de negocio.
•	Vista (veterinariaPOO.vista): interfaz gráfica con Java Swing (Vista.java, diseñada con el editor de NetBeans).
•	Controlador (veterinariaPOO.controlador): recibe los eventos de la vista, valida datos, invoca el modelo y actualiza la interfaz.
El paquete principal.veterinariapoo contiene la clase VeterinariaPOO con el método main, que crea el sistema, precarga datos iniciales y muestra la ventana.

6.	Diagrama de clases
7.	Conceptos de POO aplicados
-	Abstracción: Clases como Persona, Propietario y Mascota modelan lo común
-	Herencia: Propietario y Colaborador heredan de Persona; Recepcionista y Veterinario de Colaborador; MascotaConvencional y MascotaExotica de Mascota.
-	Polimorfismo: Mascota.CalcularRecargoBase() se implementa distinto en cada subclase
-	Encapsulamiento: Atributos privados con getters/setters y las listas se exponen como unmodifiableList.
-	Composición/Asociación/ Agregación y Uso de excepciones

8.	Datos precargados
Al iniciar, el sistema carga datos de ejemplo para poder probarlo de inmediato:
•	Recepcionistas: Laura Gómez (REC-001) y Carlos Pérez (REC-002).
•	Veterinarios: María Mora (VET-001, medicina general) y Andrés Ríos (VET-002, animales exóticos).
•	Vacunas: Rabia ($35.000), Parvovirus ($45.000), Moquillo ($40.000) y Triple felina ($50.000).

9.	¿Cómo funciona?
El sistema sigue el flujo real de una clínica veterinaria:
•	Registro: la recepcionista registra al propietario y después a su mascota (convencional o exótica). También se registran los colaboradores: recepcionistas y veterinarios.
•	Agendamiento: se crea una cita entre una mascota y un veterinario.
•	Consulta: el veterinario atiende la cita, escribe el diagnóstico y las notas médicas, y aplica vacunas (mascotas convencionales) o sedación (mascotas exóticas). La consulta queda guardada en el historial clínico de la mascota.
•	Facturación: la recepcionista selecciona la consulta pendiente y el sistema calcula el total automáticamente: 
-	Tarifa base de la consulta.
-	Más el valor de las vacunas, si hubo.
-	Más el recargo del 25 % y la sedación, si la mascota es exótica.
-	Menos el 10 % de descuento, si el propietario tiene más de 2 mascotas.
Después emite el comprobante con el detalle del cobro.
•	Historial: en cualquier momento se puede consultar el historial de una mascota, con sus consultas, vacunas aplicadas y estado de pago.
•	Ajustes: se pueden agregar vacunas y cambiar la tarifa base y el costo de sedación.

FUNCIONAMIENTO PASO GUÍA

1. Permite registrar el propietario
<img width="500" height="300" alt="Screenshot 2026-11-25 074645" src="https://github.com/user-attachments/assets/7a555e9b-ba7b-4333-a262-b1082aa3eb83" />

2. Registro de mascotas 
<img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/4bd16d2c-5702-4153-bf6d-977354a2de3a" />

3. Registro de citas
<img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/79d7ca7e-5bb4-4b5f-ad5c-5ba81a2f85b5" />

4. Registro de consultas
<img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/48b2cf76-6f1d-4f86-a989-0e72ea79f110" />

5. Genera un historial
<img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/ce75cb42-ce45-481f-a5c3-38fdfb1f8b9b" />

6. Realizar cobro y Generar factura
<img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/ca38e976-65cb-4612-ae0f-48121c92a4c8" />

Excepciones 
1. Espacios sin rellenar
<img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/fc14eba7-b78f-4a53-9b16-a2d5e7829888" />

2. Valida los digitos del telefono
<img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/b00be56f-ba45-4d78-8fe4-0b38c86c38a5" />

3. Validar Peso
<img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/ad04f05f-36a7-4897-9ef6-44e4861a78da" />

4. Valida acciones del Veterinario
<img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/8f51c531-dc28-44bb-99c0-8fc1c9bde9ff" />

5. Valida realizar una consulta con Citas agendadas
<img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/3ef94c3e-dd9c-4fc0-b202-e6ba0c51822a" />

Integrantes
•	Jhon Eudis Maldonado Pabón 1152707
•	Jimmy Leonardo Rojas Rodríguez 1152697
•	Cielo Yulieth Porras Velasquez 1152721

