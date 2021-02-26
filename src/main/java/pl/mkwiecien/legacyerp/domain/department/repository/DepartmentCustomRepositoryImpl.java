package pl.mkwiecien.legacyerp.domain.department.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DepartmentCustomRepositoryImpl implements DepartmentCustomRepository{

    @Override
    @Modifying
    public void deleteDepartmentById(Long departmentId) {

    }
}
