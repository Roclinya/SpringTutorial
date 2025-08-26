package com.tutorial.SpringTutorial.util.report;

import org.jxls.formula.FastFormulaProcessor;
import org.jxls.transform.poi.JxlsPoiTemplateFillerBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ExcelBuilder {
    public byte[] build() throws IOException {
        InputStream templateStream = getClass().getResourceAsStream("/templates/formulas_template.xls");
        ByteArrayInputStream teamplateDataStream = new ByteArrayInputStream(templateStream.readAllBytes());
        templateStream.close();

        Map<String, Object> cDataSource = createReport();

        return JxlsPoiTemplateFillerBuilder
                .newInstance()
                .withTemplate(teamplateDataStream)
                .withFormulaProcessor(new FastFormulaProcessor())
                .withRecalculateFormulasBeforeSaving(false)
                .withRecalculateFormulasOnOpening(true)
                .buildAndFill(cDataSource);
    }

    private Map<String, Object> createReport() {
        HashMap<String, Object> data = new HashMap<String, Object>();
        createReport01(data);
        return data;
    }

    private void createReport01(HashMap<String, Object> data) {
        HashMap<String, Object> cReport01 = new HashMap<String, Object>();
        cReport01.put("leader", "2024");
        cReport01.put("birthDate", "2024/09/11");
        cReport01.put("payment", 9527);
        cReport01.put("bonus", 10);

        List<HashMap<String, Object>> cDataList = new ArrayList<HashMap<String, Object>>();
        cDataList.add(cReport01);

        data.put("report1", cDataList);
    }
}