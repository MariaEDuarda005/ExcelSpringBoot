package com.springboot.excel.service;

import com.springboot.excel.doman.Course;
import com.springboot.excel.repository.CourseRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private CourseRepository courseRepository;

    public void generateExcel(HttpServletResponse response) throws IOException {

        // listas de cursos disponiveis na tabela de banco de dados
        List<Course> courses = courseRepository.findAll();

        // Um novo objeto HSSFWorkbook é criado. Este é o componente principal que representa o arquivo Excel .xls
        HSSFWorkbook workbook = new HSSFWorkbook();
        // Uma nova planilha é criada dentro do workbook com o nome "Course Info".
        HSSFSheet sheet = workbook.createSheet("Course Info");

        // criar a linha do cabeçalho
        HSSFRow row = sheet.createRow(0);

        // definindo o valor do primeiro
        // A primeira linha (índice 0) da planilha é criada e usada para os cabeçalhos das colunas. Cada célula na linha do cabeçalho é preenchida com os títulos "ID", "Name", e "Price".
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Price");

        // Itera sobre a lista de cursos e para cada curso
        int dataRowIndex = 1;

        for (Course course : courses){
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(course.getId_course());
            dataRow.createCell(1).setCellValue(course.getName());
            dataRow.createCell(2).setCellValue(course.getPrice());
            dataRowIndex ++; // A variável dataRowIndex é incrementada para mover para a próxima linha na próxima iteração.
        }

        ServletOutputStream ops = response.getOutputStream(); // Obtém o ServletOutputStream a partir da resposta HTTP. Este stream é usado para enviar dados ao cliente.
        workbook.write(ops); // escreve o conteúdo do workbook para o output stream, que efetivamente cria o arquivo Excel.
        workbook.close();
        ops.close();
    }
}
