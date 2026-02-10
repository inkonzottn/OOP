<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Project Assignments List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content">
    <div class="container-fluid">
        <h2 class="mb-4 fw-bold">Project Assignments</h2>

        <div class="table-container">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Project</th>
                    <th>Developer</th>
                    <th>Hours Spent</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
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
                                    <div>${projectAssignment.developer.firstName} ${projectAssignment.developer.lastName}</div>
                                    <small class="text-muted">${projectAssignment.developer.email}</small>
                                </div>
                            </div>
                        </td>
                        <td><span class="fw-semibold">${projectAssignment.hoursSpent}</span></td>
                        <td>
                            <#if (projectAssignment.active?? && projectAssignment.active)>
                                <span class="badge-soft badge-blue">Active</span>
                            <#else>
                                <span class="badge-soft badge-green">Completed</span>
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