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
                <a href="/${rolePath}/managers/create" class="btn-add badge-blue shadow-sm d-flex align-items-center gap-2">
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
                <#list managers as manager>
                    <tr>
                        <td>${manager.id}</td>
                        <td>
                            <#if manager.imageUrl??>
                                <img src="${manager.imageUrl}" class="dev-photo rounded-circle" width="45" height="45">
                            <#else>
                                <div class="bg-light rounded-circle text-center" style="width:45px; height:45px; line-height:45px;">?</div>
                            </#if>
                        </td>
                        <td>
                            <div>${manager.user.firstName} ${manager.user.lastName}</div>
                            <small class="text-muted">${manager.user.email}</small>
                        </td>
                        <td style="min-width: 300px;">
                            <div class="projects-list-container">
                                <#if manager.projects?? && manager.projects?size gt 0>
                                    <#list manager.projects as project>
                                        <div class="project-card">
                                            <span class="client-name">${project.customer.companyName!"PERSONAL"}</span>

                                            <a href="/projects/${project.id}" class="project-title">${project.title}</a>

                                            <div class="project-meta">
                                                <span class="project-id-tag">ID: ${project.id}</span>

                                                <#if project.status == "PROPOSAL">
                                                    <span class="badge-soft badge-yellow">Пропозиція</span>
                                                <#elseif project.status == "IN_PROGRESS">
                                                    <span class="badge-soft badge-blue">В процесі</span>
                                                <#elseif project.status == "COMPLETED">
                                                    <span class="badge-soft badge-green">Завершено</span>
                                                <#elseif project.status == "INVOICED">
                                                    <span class="badge-soft badge-purple">Виставлено рахунок</span>
                                                <#else>
                                                    <span class="bg-secondary text-white">${project.status}</span>
                                                </#if>
                                            </div>
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
                                    <button class="btn btn-link text-muted p-1 d-inline-flex align-items-center text-decoration-none"
                                            type="button"
                                            data-bs-toggle="dropdown">
                                        <i class="bi bi-caret-down-fill" style="font-size: 0.85rem;"></i>
                                    </button>

                                    <ul class="dropdown-menu dropdown-menu-end shadow-sm border-0 rounded-3">
                                        <li>
                                            <a class="dropdown-item dropdown-item-custom d-flex align-items-center gap-2" href="/${rolePath}/managers/edit/${manager.id}">
                                                <i class="bi bi-pencil-fill text-warning"></i> Редагувати
                                            </a>
                                        </li>
                                        <li><hr class="dropdown-divider opacity-50"></li>
                                        <li>
                                            <a class="dropdown-item dropdown-item-custom item-delete d-flex align-items-center gap-2" href="/${rolePath}/managers/delete/${manager.id}">
                                                <i class="bi bi-trash-fill"></i> Видалити
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </td>
                        </#if>
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