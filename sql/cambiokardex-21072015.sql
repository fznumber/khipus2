SELECT * FROM (

SELECT enc.`fecha`,enc.`tipo_doc`,`enc`.`no_doc`,`enc`.`glosa`,`det`.`debe`,0 AS haber FROM `sf_tmpenc` enc
JOIN `sf_tmpdet` det
ON det.`id_tmpenc` = enc.`id_tmpenc`
JOIN `pedidos` ped
ON ped.`id_tmpenc` = enc.`id_tmpenc` 
WHERE `det`.`cuenta` = '1421010100'
AND enc.fecha BETWEEN '2015-07-01' AND '2015-07-31' 
AND ped.`IDCLIENTE` = 62
UNION
SELECT enc.`fecha`,enc.`tipo_doc`,`enc`.`no_doc`,`enc`.`glosa`,0 AS debe,det.`haber` FROM `sf_tmpenc` enc
JOIN `sf_tmpdet` det
ON det.`id_tmpenc` = enc.`id_tmpenc`
JOIN `pago` pago
ON pago.`id_tmpenc` = enc.`id_tmpenc` 
WHERE `det`.`cuenta` = '1421010100'
AND enc.fecha BETWEEN '2015-07-01' AND '2015-07-31' 
AND pago.`IDPERSONACLIENTE` = 62
) AS kardex
ORDER BY kardex.fecha DESC;


