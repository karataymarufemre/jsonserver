package dev.karatay.jsonserver.api.controller;

import dev.karatay.jsonserver.api.constants.ApiEndpoints;
import dev.karatay.jsonserver.service.apirecord.ApiRecordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApiEndpoints.ApiRecord.API, produces = "application/json")
@RequiredArgsConstructor
public class ApiRecordController {

    private final ApiRecordService apiRecordService;

    @GetMapping("/raw/{namespace}/{domain}/{id}")
    public String getRawRecordById(@PathVariable String namespace, @PathVariable String domain, @PathVariable String id) {
        return apiRecordService.getRawRecordById(namespace, domain, id);
    }
    @GetMapping("/raw/{namespace}/{domain}")
    public String getRawRecordList(@PathVariable String namespace, @PathVariable String domain, HttpServletRequest request) {
        return apiRecordService.getRawRecordList(namespace, domain, request);
    }
    @GetMapping("/raw/{namespace}")
    public String getRawRecordList(@PathVariable String namespace) {
        return apiRecordService.getRawRecordList(namespace);
    }
    @PostMapping("/raw/{namespace}/{domain}")
    public String addRecord(@PathVariable String namespace, @PathVariable String domain, @RequestBody String jsonData) {
        return apiRecordService.addRecord(namespace, domain, jsonData);
    }
    @PutMapping("/raw/{namespace}/{domain}/{id}")
    public String updateRecordById(@PathVariable String namespace, @PathVariable String domain, @PathVariable String id, @RequestBody String jsonData) {
        return apiRecordService.updateRecordById(namespace, domain, id, jsonData);
    }
    @DeleteMapping("/raw/{namespace}/{domain}/{id}")
    public String deleteRecordById(@PathVariable String namespace, @PathVariable String domain, @PathVariable String id) {
        return apiRecordService.deleteRecordById(namespace, domain, id);
    }


}
