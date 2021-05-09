--
--Suppose that a website contains two tables, the Customers table and the Orders table. Write a SQL query to find all customers who never order anything.
--
--Table: Customers.
--
--+----+-------+
--| Id | Name  |
--+----+-------+
--| 1  | Joe   |
--| 2  | Henry |
--| 3  | Sam   |
--| 4  | Max   |
--+----+-------+
--Table: Orders.
--
--+----+------------+
--| Id | CustomerId |
--+----+------------+
--| 1  | 3          |
--| 2  | 1          |
--+----+------------+
--Using the above tables as example, return the following:
--
--+-----------+
--| Customers |
--+-----------+
--| Henry     |
--| Max       |
--+-----------+

-- 7 minutes, need to be careful when I use joins and when not to

Select c1.Name as Customers
    from Customers c1
    where c1.Id not in (
        Select c1.Id
            from Customers c1, Orders o1
        where o1.CustomerId = c1.Id
    );

