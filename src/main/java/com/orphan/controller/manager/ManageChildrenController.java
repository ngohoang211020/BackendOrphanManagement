package com.orphan.controller.manager;

import com.orphan.api.children.dto.ChildrenDto;
import com.orphan.common.service.ChildrenService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/manager/children")
@PreAuthorize("hasRole('MANAGER')")
public class ManageChildrenController {
    @Autowired
    private ChildrenService service;

    @Operation(summary = "Lấy danh sách tất cả trẻ em", description = "Trả về danh sách children", tags = {"Manager/Children"})
    @GetMapping
    public ResponseEntity<?> viewAllChildren() {
        List<ChildrenDto> childrenDtoList = service.listAllChildren();
        return ResponseEntity.ok(childrenDtoList);
    }

    @Operation(summary = "Xem chi tiết thông tin trẻ em", description = "Trả về thông tin chi tiết của 1 User tuỳ chọn theo id", tags = {"Manager/Children"})
    @GetMapping("/{id}")
    public ResponseEntity<?> viewDetailChildren(@PathVariable("id") Integer id) {
        ChildrenDto childrenDto = service.findById(id);
        return ResponseEntity.ok(childrenDto);
    }
     @Operation(summary = "Thêm thông tin trẻ em", description = "Thêm thông tin của 1 Children", tags = {"Manager/Children"})
    @PostMapping
    public ResponseEntity<?> addChildren(@RequestBody @Valid ChildrenDto childrenDto) {
        return ResponseEntity.ok(service.save(childrenDto));
    }
    @Operation(summary = "Cập nhật thông tin trẻ em", description = "Cập nhật thông tin của 1 Children theo id", tags = {"Manager/Children"})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateChildren(@PathVariable("id") Integer id, @RequestBody @Valid ChildrenDto childrenDto) {
        return ResponseEntity.ok(service.update(childrenDto, id));
    }


}
