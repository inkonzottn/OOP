<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Розробники</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content">
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold m-0">Розробники</h2>
            <#if isAdmin?? && isAdmin>
                <a href="/${rolePath}/developers/create"
                   class="btn-add badge-blue shadow-sm d-flex align-items-center gap-2">
                    + Додати розробника
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
                    <th>Спеціалізація та рівень</th>
                    <th>Ставка</th>
                    <th>Стек технологій</th>
                    <th>Поточний проєкт</th>
                    <#if isAdmin?? && isAdmin>
                        <th class="text-end">Дії</th>
                    </#if>
                </tr>
                </thead>
                <tbody>
                <#list developers as developer>
                    <#if developer.user.id != currentUserId>
                        <tr>
                            <td>${developer.id}</td>
                            <td>
                                <#if developer.imageUrl??>
                                    <img src="${developer.imageUrl}" class="dev-photo rounded-circle" width="45"
                                         height="45">
                                <#else>
                                    <div class="bg-light rounded-circle text-center"
                                         style="width:45px; height:45px; line-height:45px;">?
                                    </div>
                                </#if>
                            </td>
                            <td>
                                <div>${developer.user.firstName} ${developer.user.lastName}</div>
                                <small class="text-muted">${developer.user.email}</small>
                            </td>

                            <td class="align-middle">
                                <div class="d-flex flex-row gap-1">
                                <span class="badge-soft badge-blue">
                                    ${developer.specialization}
                                </span>
                                    <span class="badge-soft badge-gray">
                                    ${developer.qualification}
                                </span>
                                </div>
                            </td>

                            <td><span class="fw-semibold">$${developer.hourlyRate}</span></td>
                            <td>
                                <#list developer.skills as skill>
                                    <span class="badge">${skill.name}</span>
                                </#list>
                            </td>
                            <td>
                                <#if developer.currentProject??>
                                    <div class="project-card">
                                        <span class="client-name">${developer.currentProject.customer.companyName!"PERSONAL"}</span>

                                        <a href="/projects/${developer.currentProject.id}"
                                           class="project-title">${developer.currentProject.title}</a>

                                        <div class="project-meta">
                                            <span class="project-id-tag">ID: ${developer.currentProject.id}</span>

                                            <#if developer.currentProject.status == "PROPOSAL">
                                                <span class="badge-soft badge-yellow">Пропозиція</span>
                                            <#elseif developer.currentProject.status == "IN_PROGRESS">
                                                <span class="badge-soft badge-blue">В процесі</span>
                                            <#elseif developer.currentProject.status == "COMPLETED">
                                                <span class="badge-soft badge-green">Завершено</span>
                                            <#elseif developer.currentProject.status == "INVOICED">
                                                <span class="badge-soft badge-purple">Виставлено рахунок</span>
                                            <#else>
                                                <span class="badge-soft badge-gray">${developer.currentProject.status}</span>
                                            </#if>
                                        </div>
                                    </div>
                                <#else>
                                    <span class="badge-soft badge-green">Вільний</span>
                                </#if>
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
                                                <a class="dropdown-item dropdown-item-custom d-flex align-items-center gap-2"
                                                   href="/${rolePath}/developers/edit/${developer.id}">
                                                    <i class="bi bi-pencil-fill text-warning"></i> Редагувати
                                                </a>
                                            </li>
                                            <li>
                                                <hr class="dropdown-divider opacity-50">
                                            </li>
                                            <li>
                                                <form action="/${rolePath}/developers/delete/${developer.id}"
                                                      method="post"
                                                      style="display:inline;"
                                                      onsubmit="return confirm('Ви впевнені, що хочете видалити розробника ${developer.user.firstName} ${developer.user.lastName}?');">

                                                    <button type="submit"
                                                            class="dropdown-item dropdown-item-custom item-delete d-flex align-items-center gap-2"
                                                            title="Видалити">
                                                        <i class="bi bi-trash-fill"></i> Видалити
                                                    </button>
                                                </form>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </#if>
                        </tr>
                    </#if>
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