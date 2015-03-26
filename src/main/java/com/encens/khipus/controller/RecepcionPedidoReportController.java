/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.controller;

import com.encens.khipus.ejb.PedidosFacade;
import com.encens.khipus.model.Pedidos;
import net.sf.dynamicreports.adhoc.configuration.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
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

import javax.ejb.EJB;
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
    @EJB
    private PedidosFacade pedidosFacade;

    private List<Pedidos> pedidos = new ArrayList<>();
    /**
     * Creates a new instance of RecepcionPedidoReportController
     */
    public RecepcionPedidoReportController() {
    }

    private void build(ServletOutputStream stream) {

        CrosstabRowGroupBuilder<String> rowGroup = ctab.rowGroup("cliente", String.class)
                .setTotalHeader("Total:");

        CrosstabColumnGroupBuilder<String> columnGroup = ctab.columnGroup("producto", String.class).setShowTotal(false);
        CrosstabRowGroupBuilder<String> rowItemGroup = ctab.rowGroup("distribuidor", String.class).setHeaderWidth(10);
        CrosstabMeasureBuilder<String> cantidad = ctab.measure("cantidad", Integer.class, Calculation.COUNT);

        CrosstabBuilder crosstab = ctab.crosstab()
                .headerCell(cmp.text("Cliente / Producto").setStyle(Templates.boldCenteredStyle))
                .rowGroups(rowItemGroup, rowGroup)
                .columnGroups(columnGroup)
                .addMeasure(cantidad);

        try {
            report()
                    .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
                    .setTemplate(Templates.reportTemplate)
                    .title(Templates.createTitleComponent("Titulo"))
                    .summary(crosstab)
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
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

    private JRDataSource createDataSource() {
        List<Object[]> resultado = pedidosFacade.recepcionDePedidos();
        DRDataSource dataSource = new DRDataSource("cliente", "producto", "cantidad", "distribuidor");
        for (Object[] obj : resultado) {
            dataSource.add((String) obj[0],(String) obj[1],(Integer) obj[2],(String) obj[3]);
        }
        return dataSource;
    }


}
