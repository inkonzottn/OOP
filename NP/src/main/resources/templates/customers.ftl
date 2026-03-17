<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Замовники</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content">
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold m-0">Замовники</h2>
        </div>

        <div class="table-container">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Компанія</th>
                    <th>Повне ім'я</th>
                    <th>Проєкти</th>
                    <#if isAdmin?? && isAdmin>
                        <th class="text-end">Дії</th>
                    </#if>
                </tr>
                </thead>
                <tbody>
                <#list customers as customer>
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.companyName}</td>
                        <td>
                            <div>${customer.user.firstName} ${customer.user.lastName}</div>
                            <small class="text-muted">${customer.user.email}</small>
                        </td>
                        <td style="min-width: 300px;">
                            <div class="projects-list-container">
                                <#if customer.projects?? && customer.projects?size gt 0>
                                    <#list customer.projects as project>
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
                                    <span class="text-muted small">Не замовлено жодного проєкту.</span>
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
                                        <form action="/${rolePath}/customers/delete/${customer.id}" method="post"
                                              style="display:inline;"
                                              onsubmit="return confirm('Ви впевнені, що хочете видалити замовника ${customer.user.firstName} ${customer.user.lastName}?');">

                                            <button type="submit" class="dropdown-item dropdown-item-custom item-delete d-flex align-items-center gap-2" title="Видалити">
                                                <i class="bi bi-trash-fill"></i> Видалити
                                            </button>
                                        </form>
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