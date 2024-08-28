package com.springboot.excel.controller;

import com.springboot.excel.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ReportRestController {

    @Autowired
    private ReportService reportService;

    //  Quando um cliente faz uma solicitação GET para essa URL, o método generateExcelReport é chamado.
    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) throws IOException {
        //  Define o tipo de conteúdo da resposta como "application/octet-stream",
        //  o que indica que o conteúdo é um arquivo binário. Isso faz com que o navegador
        //  trate o conteúdo como um arquivo a ser baixado.
        response.setContentType("application/octet-stream");
        //  Define o cabeçalho Content-Disposition para a resposta.
        String headerKey = "Content-Disposition";
        // Define o valor do cabeçalho Content-Disposition para indicar que o conteúdo deve
        // ser tratado como um arquivo anexo com o nome courses.xls. Isso sugere ao navegador
        // que deve exibir uma caixa de diálogo para download.
        String headerValue = "attachment;filename=courses.xls";

        response.setHeader(headerKey, headerValue);

        reportService.generateExcel(response);
    }
}
