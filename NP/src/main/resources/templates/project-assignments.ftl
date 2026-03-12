<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Завдання проєкту</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content">
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold m-0">Завдання проєкту</h2>
            <#if isAuth?? && (isAdmin?? && isAdmin) || (isManager?? && isManager) || (isDeveloper?? && isDeveloper)>
                <a href="/${rolePath}/project-assignments/create" class="btn-add badge-blue shadow-sm d-flex align-items-center gap-2">
                    + Додати завдання
                </a>
            </#if>
        </div>

        <div class="table-container">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Проєкт</th>
                    <th>Розробник</th>
                    <th>Часу витрачено</th>
                    <th>Статус</th>
                    <#if isAuth?? && (isAdmin?? && isAdmin) || (isManager?? && isManager) || (isDeveloper?? && isDeveloper)>
                        <th class="text-end">Дії</th>
                    </#if>
                </tr>
                </thead>
                <tbody>
                <#if projectAssignments?? && projectAssignments?size gt 0>
                <#list projectAssignments as projectAssignment>
                    <tr>
                        <td>${projectAssignment.id}</td>
                        <td><span class="fw-semibold">${projectAssignment.project.title}</span></td>

                        <td>
                            <div style="display: flex; flex-direction: row; justify-content: start; gap: 10pt">
                                <#if projectAssignment.developer.imageUrl??>
                                    <img src="${projectAssignment.developer.imageUrl}" class="dev-photo rounded-circle" width="45" height="45">
                                <#else>
                                    <div class="bg-light rounded-circle text-center" style="width:45px; height:45px; line-height:45px;">?</div>
                                </#if>

                                <div>
                                    <div>${projectAssignment.developer.user.firstName} ${projectAssignment.developer.user.lastName}</div>
                                    <small class="text-muted">${projectAssignment.developer.user.email}</small>
                                </div>
                            </div>
                        </td>
                        <td><span class="fw-semibold">${projectAssignment.hoursSpent}</span></td>
                        <td>
                            <#if (projectAssignment.active?? && projectAssignment.active)>
                                <span class="badge-soft badge-blue">Активне</span>
                            <#else>
                                <span class="badge-soft badge-green">Завершено</span>
                            </#if>
                        </td>
                        <#if isAuth?? && isAuth && (isAdmin?? && isAdmin) || (isManager?? && isManager) || (isDeveloper?? && isDeveloper)>
                            <td class="text-end">
                                <div class="dropdown">
                                    <button class="btn btn-link text-muted p-1 d-inline-flex align-items-center text-decoration-none"
                                            type="button"
                                            data-bs-toggle="dropdown">
                                        <i class="bi bi-caret-down-fill" style="font-size: 0.85rem;"></i>
                                    </button>

                                    <ul class="dropdown-menu dropdown-menu-end shadow-sm border-0 rounded-3">
                                        <li>
                                            <a class="dropdown-item dropdown-item-custom d-flex align-items-center gap-2" href="/${rolePath}/project-assignments/edit/${project.id}">
                                                <i class="bi bi-pencil-fill text-warning"></i> Редагувати
                                            </a>
                                        </li>
                                        <li><hr class="dropdown-divider opacity-50"></li>
                                        <li>
                                            <a class="dropdown-item dropdown-item-custom item-delete d-flex align-items-center gap-2" href="/${rolePath}/project-assignments/delete/${project.id}">
                                                <i class="bi bi-trash-fill"></i> Видалити
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </td>
                        </#if>
                    </tr>
                </#list>
                <#else>
                    <tr>
                        <td colspan="${(isAuth?? && (isAdmin?? && isAdmin) || (isManager?? && isManager) || (isDeveloper?? && isDeveloper))?string('6', '5')}" class="text-center py-2 text-muted">
                            <span class="text-muted small">Немає жодних завдань.</span>
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