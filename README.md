# Blood Pressure Monitoring System

The Blood-Pressure-Monitoring-System (BPMS) is a simulated IoT environment for medical assistance. The goal of this project is to demonstrate a real life scenario of the usage of medical devices (such as blood pressure sensors) that feed patients' data to a doctor's terminal. 


## Description

The system consists of three separate modules, namely the Simulator, the Server and the User-App. The Simulator generates the measurements, through random generators and acts as a blood pressure sensor. The Server handles the incoming requests from the Simulator and the User-App and saves the data in persistence storage (Microsoft SQL Server).
The Health monitoring application (GUI) allows the user a) to see on the screen of the PC the measurements that have been registered, b) to print them, c) to sort them, d) to search for a specific record.

The first stage of the system was implemented with the Java API for WebSocket and the second stage implements a client/server architecture with HTTP. 

![Blood-Pressure-Monitoring-System-based-on-DBMS](https://github.com/ThomasPappas00/Blood-Pressure-Monitoring-System/assets/75483971/9e7010ab-bfa6-4215-b16d-13c0a72f9c15)

## License

[MIT](https://choosealicense.com/licenses/mit/)
