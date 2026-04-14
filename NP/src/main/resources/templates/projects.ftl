<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Проєкти</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content">
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold m-0">Проєкти</h2>

            <#if isAuth?? && (isCustomer?? && isCustomer)>
                <a href="/${rolePath}/projects/create"
                   class="btn-add badge-blue shadow-sm d-flex align-items-center gap-2">
                    + Оформити заявку
                </a>
            </#if>
        </div>

        <#if successMessage??>
            <div class="alert alert-success alert-dismissible fade show shadow-sm" role="alert">
                <i class="bi bi-check-circle-fill me-2"></i>${successMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </#if>
        <#if errorMessage??>
            <div class="alert alert-danger alert-dismissible fade show shadow-sm" role="alert">
                <i class="bi bi-exclamation-triangle-fill me-2"></i>${errorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </#if>

        <div class="table-container">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Назва</th>
                    <th>Замовник</th>
                    <th>Менеджер</th>
                    <th class="text-center">Статус</th>
                    <th>Вартість</th>
                    <th>Опис</th>
                    <#if isAuth?? && (isAdmin?? && isAdmin) || (isManager?? && isManager)>
                        <th class="text-end">Дії</th>
                    </#if>
                </tr>
                </thead>
                <tbody>
                <#if projects?? && projects?size gt 0>
                    <#list projects as project>
                        <tr>
                            <td>${project.id}</td>
                            <td class="fw-semibold" style="min-width: 100px; max-width: 150px">${project.title}</td>
                            <td style="min-width: 180px; flex-direction: column;">
                                <div>${project.customer.user.firstName} ${project.customer.user.lastName}</div>
                                <small class="text-muted"> ${project.customer.companyName}</small>
                                <small class="text-muted">${project.customer.user.email}</small>
                            </td>
                            <td style="min-width: 180px;">
                                <#if project.manager??>
                                    <div>${project.manager.user.firstName} ${project.manager.user.lastName}</div>
                                    <small class="text-muted">${project.manager.user.email}</small>
                                <#else>
                                    <span class="badge bg-secondary text-light">
                                    <i class="bi bi-person-x me-1"></i> Не призначено
                                </span>
                                </#if>
                            </td>

                            <td class="text-center">
                                <div class="project-meta">
                                    <#if project.status == "PROPOSAL">
                                        <span class="badge-soft badge-yellow">На розгляді</span>
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
                            </td>
                            <td>
                                <#if project.invoices?? && project.invoices?size gt 0>
                                    <div class="d-flex flex-column gap-1">
                                        <#list project.invoices as inv>
                                            <div class="badge <#if inv.status == 'PAID'>bg-success<#elseif inv.status == 'CANCELLED'>bg-danger<#else>bg-warning text-dark</#if>">
                                                $${inv.finalPrice?string("0.00")} (${inv.status})
                                            </div>
                                        </#list>
                                    </div>
                                <#else>
                                    <span class="text-muted small">Рахунків немає</span>
                                </#if>
                            </td>
                            <td class="description-cell">
                                <div class="description-truncate" title="${project.description}">
                                    ${project.description}
                                </div>
                            </td>
                            <#if isAuth?? && (isAdmin?? && isAdmin) || (isManager?? && isManager)>
                                <td class="text-end">
                                    <div class="dropdown">
                                        <button class="btn btn-link text-muted p-1 d-inline-flex align-items-center text-decoration-none"
                                                type="button"
                                                data-bs-toggle="dropdown">
                                            <i class="bi bi-caret-down-fill" style="font-size: 0.85rem;"></i>
                                        </button>

                                        <ul class="dropdown-menu dropdown-menu-end shadow-sm border-0 rounded-3">
                                            <li>
                                                <a class="dropdown-item dropdown-item-custom d-flex align-items-center gap-2"
                                                   href="/${rolePath}/projects/edit/${project.id}">
                                                    <i class="bi bi-pencil-fill text-warning"></i> Редагувати
                                                </a>
                                            </li>
                                            <li>
                                                <hr class="dropdown-divider opacity-50">
                                            </li>
                                            <#if isAuth?? && (isAdmin?? && isAdmin) || (isManager?? && isManager)>
                                                <li>
                                                    <a class="dropdown-item dropdown-item-custom d-flex align-items-center gap-2"
                                                       href="/${rolePath}/invoices/create/${project.id}">
                                                        <i class="bi bi-cash-coin text-success"></i> Виставити рахунок
                                                    </a>
                                                </li>

                                            </#if>
                                            <li>
                                                <hr class="dropdown-divider opacity-50">
                                            </li>
                                            <li>
                                                <form action="/admin/projects/delete/${project.id}" method="post"
                                                      onsubmit="return confirm('Ви впевнені? Проєкт буде видалено зі списків усіх розробників. Цю дію неможливо скасувати!');"
                                                      style="display:inline;">

                                                    <button type="submit" class="dropdown-item dropdown-item-custom d-flex align-items-center gap-2">
                                                        <i class="bi bi-trash3 text-danger"></i> Видалити
                                                    </button>
                                                </form>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </#if>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td colspan="${(isAuth?? && (isAdmin?? && isAdmin) || (isManager?? && isManager))?string('8', '7')}"
                            class="text-center py-2 text-muted">
                            <span class="text-muted small">Немає жодних проєктів.</span>
                        </td>
                    </tr>
                </#if>
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