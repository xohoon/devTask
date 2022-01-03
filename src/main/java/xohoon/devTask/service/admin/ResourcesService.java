package xohoon.devTask.service.admin;

import xohoon.devTask.domain.entity.admin.Resources;

import java.util.List;

public interface ResourcesService {
    Resources getResources(long id);

    List<Resources> getResources();

    void createResources(Resources Resources);

    void deleteResources(long id);
}
