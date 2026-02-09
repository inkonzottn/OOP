<#--<!DOCTYPE html>-->
<#--<html lang="en">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <title>Managers</title>-->
<#--</head>-->
<#--<body>-->
<#--<h1>Managers list</h1>-->

<#--<table>-->
<#--    <thead>-->
<#--    <tr>-->

<#--    </tr>-->
<#--    </thead>-->
<#--    <tbody>-->

<#--    <#if managers??>-->
<#--        <#list managers as manager>-->
<#--            <tr>-->

<#--                <td>-->
<#--                    <#if manager.projects?? && manager.projects?size gt 0>-->
<#--                        <ul>-->
<#--                            <#list manager.projects as project>-->
<#--                                <li>${project.title} (ID: ${project.id})</li>-->
<#--                            </#list>-->
<#--                        </ul>-->
<#--                    <#else>-->
<#--                        No projects yet.-->
<#--                    </#if>-->
<#--                </td>-->
<#--            </tr>-->
<#--        </#list>-->
<#--    <#else>-->
<#--        No managers yet.-->
<#--    </#if>-->
<#--    </tbody>-->
<#--</table>-->
<#--</body>-->
<#--</html>-->





<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manager List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content">
    <div class="container-fluid">
        <h2 class="mb-4 fw-bold">Managers</h2>

        <div class="table-container">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Photo</th>
                    <th>Full name</th>
                    <th>Projects</th>
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
                            <div>${manager.firstName} ${manager.lastName}</div>
                            <small class="text-muted">${manager.email}</small>
                        </td>
                        <td class="align-top">
                            <#if manager.projects?? && manager.projects?size gt 0>
                                <div class="d-flex flex-wrap gap-2">
                                    <div class="d-flex flex-wrap gap-3">
                                        <#list manager.projects as project>
                                            <div class="project-card border rounded p-3 bg-white shadow-sm" style="min-width: 200px;">
                                                <#-- Компанія замовника -->
                                                <div class="text-uppercase text-muted fw-bold mb-1" style="font-size: 0.7rem; letter-spacing: 1px;">
                                                    ${project.customer.companyName!"No Company"}
                                                </div>

                                                <#-- Назва проекту -->
                                                <div class="mb-2">
                                                    <a href="/projects/${project.id}" class="project-title text-decoration-none fw-bold" style="color: #0d6efd;">
                                                        ${project.title}
                                                    </a>
                                                </div>

                                                <div class="d-flex justify-content-between align-items-center">
                                                    <span class="badge bg-light text-dark border">ID: ${project.id}</span>

                                                    <#-- Пряма логіка статусів без макросів (щоб точно не злетіло) -->
                                                    <#if project.status == "PROPOSAL">
                                                        <span class="badge bg-warning text-dark">PROPOSAL</span>
                                                    <#elseif project.status == "IN_PROGRESS">
                                                        <span class="badge bg-primary">IN PROGRESS</span>
                                                    <#elseif project.status == "COMPLETED">
                                                        <span class="badge bg-success">COMPLETED</span>
                                                    <#elseif project.status == "INVOICED">
                                                        <span class="badge bg-info text-dark">INVOICED</span>
                                                    <#else>
                                                        <span class="badge bg-secondary">${project.status!"NEW"}</span>
                                                    </#if>
                                                </div>
                                            </div>
                                        </#list>
                                    </div>
                                </div>
                            <#else>
                                <span class="badge bg-light text-muted fw-normal italic">No active projects</span>
                            </#if>
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