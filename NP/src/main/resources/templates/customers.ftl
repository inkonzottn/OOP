<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content">
    <div class="container-fluid">
        <h2 class="mb-4 fw-bold">Customers</h2>

        <div class="table-container">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Company</th>
                    <th>Full name</th>
                    <th>Projects</th>
                </tr>
                </thead>
                <tbody>
                <#list customers as customer>
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.companyName}</td>
                        <td>
                            <div>${customer.firstName} ${customer.lastName}</div>
                            <small class="text-muted">${customer.email}</small>
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
                                        </div>
                                    </#list>
                                <#else>
                                    <span class="text-muted small">No projects assigned</span>
                                </#if>
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