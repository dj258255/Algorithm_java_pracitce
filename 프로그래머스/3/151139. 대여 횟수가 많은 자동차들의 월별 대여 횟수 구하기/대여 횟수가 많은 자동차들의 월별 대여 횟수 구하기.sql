-- 코드를 입력하세요
SELECT m.MONTH, m.CAR_ID, m.RECORDS
FROM (
    SELECT MONTH(START_DATE) AS MONTH, 
           CAR_ID, 
           COUNT(*) AS RECORDS
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE DATE_FORMAT(START_DATE, '%Y-%m') BETWEEN '2022-08' AND '2022-10'
    GROUP BY MONTH(START_DATE), CAR_ID
) m
JOIN (
    SELECT CAR_ID, SUM(RECORDS) AS TotalRecords
    FROM (
        SELECT CAR_ID, COUNT(*) AS RECORDS
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE DATE_FORMAT(START_DATE, '%Y-%m') BETWEEN '2022-08' AND '2022-10'
        GROUP BY MONTH(START_DATE), CAR_ID
    ) s
    GROUP BY CAR_ID
    HAVING TotalRecords >= 5
) t ON m.CAR_ID = t.CAR_ID
ORDER BY m.MONTH ASC, m.CAR_ID DESC;
