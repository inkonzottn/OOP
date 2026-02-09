<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Developers List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content">
    <div class="container-fluid">
        <h2 class="mb-4 fw-bold">Developers</h2>

        <div class="table-container">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Photo</th>
                    <th>Full Name</th>
                    <th>Role & Level</th>
                    <th>Rate</th>
                    <th>Tech Stack</th>
                    <th>Project</th>
                </tr>
                </thead>
                <tbody>
                <#list developers as developer>
                    <tr>
                        <td>${developer.id}</td>
                        <td>
                            <#if developer.imageUrl??>
                                <img src="${developer.imageUrl}" class="dev-photo rounded-circle" width="45" height="45">
                            <#else>
                                <div class="bg-light rounded-circle text-center" style="width:45px; height:45px; line-height:45px;">?</div>
                            </#if>
                        </td>
                        <td>
                            <div>${developer.firstName} ${developer.lastName}</div>
                            <small class="text-muted">${developer.email}</small>
                        </td>
                        <td>
                            <span class="badge bg-dark">${developer.specialization}</span><br>
                            <span class="badge bg-secondary mt-1">${developer.qualification}</span>
                        </td>
                        <td><span class="fw-semibold">$${developer.hourlyRate}</span></td>
                        <td>
                            <#list developer.skills as skill>
                                <span class="badge skill-badge">${skill.name}</span>
                            </#list>
                        </td>
                        <td>
                            <#if developer.currentProject??>
                                <span class="badge bg-info text-dark">${developer.currentProject.title}</span>
                            <#else>
                                <span class="badge bg-light text-muted">Free</span>
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