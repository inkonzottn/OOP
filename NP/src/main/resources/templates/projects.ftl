<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Project List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content">
    <div class="container-fluid">
        <h2 class="mb-4 fw-bold">Projects</h2>

        <div class="table-container">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Customer</th>
                    <th>Manager</th>
                    <th class="text-center">Status</th>
                    <th>Cost</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                <#list projects as project>
                    <tr>
                        <td>${project.id}</td>
                        <td class="fw-semibold" style="min-width: 100px; max-width: 150px">${project.title}</td>
                        <td style="min-width: 180px; flex-direction: column;">
                            <div>${project.customer.firstName} ${project.customer.lastName}</div>
                            <small class="text-muted"> ${project.customer.companyName}</small>
                            <small class="text-muted">${project.customer.email}</small>
                        </td>
                        <td style="min-width: 180px;">
                            <div>${project.manager.firstName} ${project.manager.lastName}</div>
                            <small class="text-muted">${project.manager.email}</small>
                        </td>

                        <td class="text-center">
                            <div class="project-meta">
                                <#if project.status == "PROPOSAL">
                                    <span class="badge-soft badge-yellow">Proposal</span>
                                <#elseif project.status == "IN_PROGRESS">
                                    <span class="badge-soft badge-blue">In Progress</span>
                                <#elseif project.status == "COMPLETED">
                                    <span class="badge-soft badge-green">Completed</span>
                                <#elseif project.status == "INVOICED">
                                    <span class="badge-soft badge-purple">Invoiced</span>
                                <#else>
                                    <span class="bg-secondary text-white">${project.status}</span>
                                </#if>
                            </div>
                        </td>
                        <td><span class="fw-semibold">$${project.totalCost}</span></td>
                        <td class="description-cell">
                            <div class="description-truncate" title="${project.description}">
                                ${project.description}
                            </div>
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