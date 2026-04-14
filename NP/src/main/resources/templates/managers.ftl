<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Менеджери</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content">
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold m-0">Менеджери</h2>
            <#if isAdmin?? && isAdmin>
                <a href="/${rolePath}/managers/create"
                   class="btn-add badge-blue shadow-sm d-flex align-items-center gap-2">
                    + Додати менеджера
                </a>
            </#if>
        </div>

        <div class="table-container">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Фото</th>
                    <th>Повне ім'я</th>
                    <th>Проєкти</th>
                    <#if isAdmin?? && isAdmin>
                        <th class="text-end">Дії</th>
                    </#if>
                </tr>
                </thead>
                <tbody>
                <#list managers as manager, currentProjects>
                    <tr>
                        <td>${manager.id}</td>
                        <td>
                            <#if manager.imageUrl??>
                                <img src="${manager.imageUrl}" class="dev-photo rounded-circle" width="45" height="45">
                            <#else>
                                <div class="bg-light rounded-circle text-center fw-bold text-primary"
                                     style="width:45px; height:45px; line-height:45px;">
                                    ${manager.user.firstName[0]}${manager.user.lastName[0]}
                                </div>
                            </#if>
                        </td>
                        <td>
                            <div class="fw-semibold text-dark">${manager.user.firstName} ${manager.user.lastName}</div>
                            <small class="text-muted">${manager.user.email}</small>
                        </td>
                        <td style="min-width: 300px;">
                            <div class="projects-list-container d-flex flex-wrap gap-2">
                                <#if currentProjects?? && currentProjects?size gt 0>
                                    <#list currentProjects as project>
                                        <div class="badge bg-light text-dark border border-secondary-subtle p-2">
                                            <i class="bi bi-folder2 me-1"></i> ${project.title}
                                            <span class="ms-1 badge rounded-pill bg-secondary" style="font-size: 0.6rem;">${project.status}</span>
                                        </div>
                                    </#list>
                                <#else>
                                    <span class="text-muted small">Не призначено жодного проєкту.</span>
                                </#if>
                            </div>
                        </td>
                        <#if isAdmin?? && isAdmin>
                            <td class="text-end">
                                <div class="dropdown">
                                    <button class="btn btn-light btn-sm rounded-circle p-2 dropdown-toggle-no-caret"
                                            type="button" data-bs-toggle="dropdown">
                                        <i class="bi bi-three-dots-vertical"></i>
                                    </button>

                                    <ul class="dropdown-menu dropdown-menu-end shadow-sm border-0 rounded-3">
                                        <li>
                                            <a class="dropdown-item d-flex align-items-center gap-2 py-2"
                                               href="/${rolePath}/managers/edit/${manager.id}">
                                                <i class="bi bi-pencil-fill text-warning"></i> Редагувати
                                            </a>
                                        </li>
                                        <li><hr class="dropdown-divider opacity-50"></li>
                                        <li>
                                            <form action="/${rolePath}/managers/delete/${manager.id}" method="post"
                                                  onsubmit="return confirm('Ви впевнені, що хочете видалити цього менеджера?');">
                                                <button type="submit" class="dropdown-item text-danger d-flex align-items-center gap-2 py-2">
                                                    <i class="bi bi-trash-fill"></i> Видалити
                                                </button>
                                            </form>
                                        </li>
                                    </ul>
                                </div>
                            </td>
                        </#if>
                    </tr>
                <#else>
                    <tr>
                        <td colspan="5" class="text-center py-4 text-muted">
                            <i class="bi bi-people fs-2 d-block mb-2 opacity-50"></i>
                            Менеджерів поки немає
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>