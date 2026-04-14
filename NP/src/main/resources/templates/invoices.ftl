<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Рахунки</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content">
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold m-0">Рахунки</h2>
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
                    <th>Проєкт</th>
                    <th>Замовник</th>
                    <th>Менеджер</th>
                    <th>Вартість</th>
                    <th>Дата</th>
                    <th>Статус</th>
                    <#if isAuth?? && (isAdmin?? && isAdmin) || (isManager?? && isManager)>
                        <th class="text-end">Дії</th>
                    </#if>
                </tr>
                </thead>
                <tbody>
                <#if invoices?? && invoices?size gt 0>
                <#list invoices as invoice>
                    <tr onclick="window.location.href='/${rolePath}/invoices/${invoice.id}'" style="cursor: pointer;" class="table-hover-row">
                        <td>${invoice.id}</td>
                        <td><span class="fw-semibold">${invoice.project.title}</span></td>
                        <td style="min-width: 180px;">
                            <div>${invoice.project.customer.user.firstName} ${invoice.project.customer.user.lastName}</div>
                            <small class="text-muted"> ${invoice.project.customer.companyName}</small>
                            <small class="text-muted">${invoice.project.customer.user.email}</small>
                        </td>
                        <td style="min-width: 180px;">
                            <div>${invoice.project.manager.user.firstName} ${invoice.project.manager.user.lastName}</div>
                            <small class="text-muted">${invoice.project.manager.user.email}</small>
                        </td>
                        <td><span class="fw-semibold">$${invoice.finalPrice}</span></td>
                        <td>${invoice.createdAt}</td>
                        <td class="text-center">
                            <div class="project-meta">
                                <#if invoice.status == "CANCELLED">
                                    <span class="badge-soft badge-yellow">Відхилено</span>
                                <#elseif invoice.status == "PAID">
                                    <span class="badge-soft badge-green">Оплачено</span>
                                <#elseif invoice.status == "PENDING">
                                    <span class="badge-soft badge-blue">В очікуванні</span>
                                </#if>
                            </div>
                        </td>
                        <#if isAuth?? && isAuth && (isAdmin?? && isAdmin) || (isManager?? && isManager)>
                            <td class="text-end">
                            <div class="dropdown">
                                <button class="btn btn-link text-muted p-1 d-inline-flex align-items-center text-decoration-none"
                                        type="button"
                                        data-bs-toggle="dropdown" onclick="event.stopPropagation();">
                                    <i class="bi bi-caret-down-fill" style="font-size: 0.85rem;"></i>
                                </button>

                                <ul class="dropdown-menu dropdown-menu-end shadow-sm border-0 rounded-3">
                                    <li>
                                        <a class="dropdown-item dropdown-item-custom d-flex align-items-center gap-2" href="/${rolePath}/invoices/edit/${invoice.id}" onclick="event.stopPropagation();">
                                            <i class="bi bi-pencil-fill text-warning"></i> Редагувати
                                        </a>
                                    </li>
                                    <li><hr class="dropdown-divider opacity-50"></li>
                                </ul>
                            </div>
                        </td>
                        </#if>
                    </tr>
                </#list>
                <#else>
                    <tr>
                        <td colspan="${(isAuth?? && (isAdmin?? && isAdmin) || (isManager?? && isManager))?string('8', '7')}" class="text-center py-2 text-muted">
                            <span class="text-muted small">Немає жодних рахунків.</span>
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