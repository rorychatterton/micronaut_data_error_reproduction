## Error

This repository exists to reproduce an error related to typedef's for custom wrappers with Micronaut Data

## Issue
When using Micronaut Data Typedef, Get/Create/Update/Delete actions work as expected with custom types, but Hibernate Queries do not.

Instead of successfully returning a result that returns plural, the following error is thrown:
```
java.lang.UnsupportedOperationException: Unwrap strategy not known for this Java type : io.ozee.TestID
	at org.hibernate.type.descriptor.java.spi.UnknownBasicJavaType.unwrap(UnknownBasicJavaType.java:60)
	...
```

