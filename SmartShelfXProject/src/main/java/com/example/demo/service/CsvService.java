package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.AuditService;

import com.opencsv.CSVReader;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


@Service
public class CsvService {
    private final ProductRepository productRepo;
    private final AuditService auditService;

    public CsvService(ProductRepository productRepo, AuditService auditService){
        this.productRepo = productRepo; this.auditService = auditService;
    }

    @Transactional
    public List<Product> importProducts(MultipartFile file, String actor) throws Exception {
        try (InputStreamReader isr = new InputStreamReader(file.getInputStream());
             CSVReader reader = new CSVReader(isr)) {
            List<String[]> rows = reader.readAll();
            List<Product> created = new ArrayList<>();
            for (int i=1; i<rows.size(); i++) { // skip header
                String[] r = rows.get(i);
                Product p = new Product();
                p.setName(r[0]); p.setSku(r[1]); p.setCategory(r[2]);
                p.setDescription(r.length>3 ? r[3] : null);
                p.setPrice(r.length>4 ? Double.valueOf(r[4]) : 0.0);
                productRepo.save(p);
                auditService.log("Product", p.getId(), "CREATE_FROM_CSV", actor, "Imported row " + i);
                created.add(p);
            }
            return created;
        }
    }

    public void exportProducts(Writer writer) throws Exception {
        StatefulBeanToCsv<Product> beanToCsv = new StatefulBeanToCsvBuilder<Product>(writer).build();
        List<Product> all = productRepo.findAll();
        beanToCsv.write(all);
    }
}
