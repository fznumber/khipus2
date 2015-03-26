/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.controller;

import com.encens.khipus.model.Pedidos;
import net.sf.dynamicreports.adhoc.configuration.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.*;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import com.encens.khipus.util.Templates;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Diego
 */
@Named(value = "recepcionPedidoReportController")
@RequestScoped
public class RecepcionPedidoReportController {

    private List<Pedidos> pedidos = new ArrayList<>();
    /**
     * Creates a new instance of RecepcionPedidoReportController
     */
    public RecepcionPedidoReportController() {
    }

    private void build(ServletOutputStream stream) {
        AdhocConfiguration configuration = new AdhocConfiguration();
        AdhocReport report = new AdhocReport();
        configuration.setReport(report);

        //columns
        AdhocColumn column = new AdhocColumn();
        column.setName("quantity");
        report.addColumn(column);
        column = new AdhocColumn();
        column.setName("unitprice");
        report.addColumn(column);
        //groups
        /*AdhocGroup group = new AdhocGroup();
        group.setName("item");
        report.addGroup(group);
        //subtotal
        AdhocSubtotal subtotal = new AdhocSubtotal();
        subtotal.setName("quantity");
        subtotal.setCalculation(AdhocCalculation.COUNT);
        report.addSubtotal(subtotal);
        subtotal = new AdhocSubtotal();
        subtotal.setCalculation(AdhocCalculation.SUM);
        subtotal.setName("unitprice");
        report.addSubtotal(subtotal);*/

        CrosstabRowGroupBuilder<String> rowGroup = ctab.rowGroup("state", String.class)
                .setTotalHeader("Total for state");

        CrosstabColumnGroupBuilder<String> columnGroup = ctab.columnGroup("item", String.class);

        CrosstabBuilder crosstab = ctab.crosstab()
                .headerCell(cmp.text("State / Item").setStyle(Templates.boldCenteredStyle))
                .rowGroups(rowGroup)
                .columnGroups(columnGroup)
                .measures(
                        ctab.measure("Quantity", "quantity", Integer.class, Calculation.SUM),
                        ctab.measure("Unit price", "unitprice", BigDecimal.class, Calculation.SUM));

        try {
            report()
                    .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
                    .setTemplate(Templates.reportTemplate)
                    .title(Templates.createTitleComponent("Crosstab"))
                    .summary(crosstab)
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    //.groupBy(distribuidor)
                    //.subtotalsAtSummary(sbt.sum())
                    .toPdf(stream);
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    public void imprimir(List<Pedidos> pedidosList) throws IOException, JRException {
        pedidos = pedidosList;
        exportarPDF();
    }
    public void imprimir() throws IOException, JRException {
        exportarPDF();
    }

    private void exportarPDF() throws JRException, IOException {


        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename=jsfReporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
        build(stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    /*select concat(pe.codigo.secuencia,'-',pe.cliente.nom,' ',pe.cliente.ap,' ',pe.cliente.am,pe.cliente.razonsocial) as CLIENTE
    ,articulos.invArticulos.descri as PRODUCTO
    ,articulos.cantidad + articulos.reposicion as CANTIDAD
    ,concat(pe.cliente.territoriotrabajo.distribuidor.nom,' ',pe.cliente.territoriotrabajo.distribuidor.ap,' ',pe.cliente.territoriotrabajo.distribuidor.am) as DISTRIBUIDOR
    from Pedidos pe join pe.articulosPedidos articulos*/
    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("state", "item", "quantity", "unitprice","distribuidor");
        dataSource.add("New York", "Notebook", 1, new BigDecimal(500),"PEDRO");
        dataSource.add("New York", "DVD", 5, new BigDecimal(30),"PEDRO");
        dataSource.add("New York", "DVD", 2, new BigDecimal(45),"PEDRO");
        dataSource.add("New York", "DVD", 4, new BigDecimal(36),"PEDRO");
        dataSource.add("New York", "DVD", 5, new BigDecimal(41),"PEDRO");
        dataSource.add("New York", "Book", 2, new BigDecimal(11),"PEDRO");
        dataSource.add("New York", "Book", 8, new BigDecimal(9),"CARLOS");
        dataSource.add("New York", "Book", 6, new BigDecimal(14),"CARLOS");

        dataSource.add("Washington", "Notebook", 1, new BigDecimal(610),"CARLOS");
        dataSource.add("Washington", "DVD", 4, new BigDecimal(40),"CARLOS");
        dataSource.add("Washington", "DVD", 6, new BigDecimal(35),"CARLOS");
        dataSource.add("Washington", "DVD", 3, new BigDecimal(46),"CARLOS");
        dataSource.add("Washington", "DVD", 2, new BigDecimal(42),"CARLOS");
        dataSource.add("Washington", "Book", 3, new BigDecimal(12),"CARLOS");
        dataSource.add("Washington", "Book", 9, new BigDecimal(8),"MARIO");
        dataSource.add("Washington", "Book", 4, new BigDecimal(14),"MARIO");
        dataSource.add("Washington", "Book", 5, new BigDecimal(10),"MARIO");

        dataSource.add("Florida", "Notebook", 1, new BigDecimal(460),"MARIO");
        dataSource.add("Florida", "DVD", 3, new BigDecimal(49),"MARIO");
        dataSource.add("Florida", "DVD", 4, new BigDecimal(32),"MARIO");
        dataSource.add("Florida", "DVD", 2, new BigDecimal(47),"MARIO");
        dataSource.add("Florida", "Book", 4, new BigDecimal(11),"MARIO");
        dataSource.add("Florida", "Book", 8, new BigDecimal(6),"MARIO");
        dataSource.add("Florida", "Book", 6, new BigDecimal(16),"MARIO");
        dataSource.add("Florida", "Book", 3, new BigDecimal(18),"MARIO");
        return dataSource;
    }


}
