package com.example.demo.service;

import com.example.demo.entity.AuditLog;

import com.example.demo.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private final AuditLogRepository auditRepo;
    public AuditService(AuditLogRepository auditRepo){ this.auditRepo = auditRepo; }

    public void log(String entity, Long entityId, String action, String actor, String details){
        AuditLog a = new AuditLog();
        a.setEntityName(entity);
        a.setEntityId(entityId);
        a.setAction(action);
        a.setChangedBy(actor);
        a.setDetails(details);
        auditRepo.save(a);
    }
}
