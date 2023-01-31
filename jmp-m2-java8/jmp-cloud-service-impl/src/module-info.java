import jmp.cloud.service.impl.ServiceImpl;
import jmp.service.api.Service;

module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;
    exports jmp.cloud.service.impl;
    provides Service with ServiceImpl;
}
