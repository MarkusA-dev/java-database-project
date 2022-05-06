USE Northwind
GO

IF NOT EXISTS(SELECT 1 FROM sys.views WHERE Name = 'java database view')
BEGIN
	EXEC('CREATE VIEW [java database view] AS SELECT o.OrderID, p.ProductName, c.ContactName, od.Quantity * od.UnitPrice AS cost, o.OrderDate FROM Orders o, Products p, [Order Details] od, Customers c
	WHERE o.OrderID=od.OrderID
	AND od.ProductID=p.ProductID
	AND o.CustomerID=c.CustomerID')
END
GO