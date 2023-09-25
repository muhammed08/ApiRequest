In this version, we modified the api to have the ability to access and retrieve data using an 
in memory db (h2). Reactive curd repository is used along with reactive h2 to make the application non blocking.

The table stores information of rates associated with currency conversion. The api end points are get/currencyCode and getAll.

Accessing DB is done in non blocking way and the publisher is subscribed on boundelastic threads. BoundElastic is a special type of thread pool used for I/O operations/DB connections. Other commonly used scheduler are single (blocking) and parallel.

The testresult function delays the code, to show the async nature of flatmap. To process the data in sequential, use flatmapsequential.

A custom logger is added to log thread information along with timestamp for better tracking of service.