<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Invoices List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content">
    <div class="container-fluid">
        <h2 class="mb-4 fw-bold">Invoices</h2>

        <div class="table-container">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Project</th>
                    <th>Customer</th>
                    <th>Manager</th>
                    <th>Cost</th>
                    <th>Issued Date</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <#list invoices as invoice>
                    <tr>
                        <td>${invoice.id}</td>
                        <td><span class="fw-semibold">${invoice.project.title}</span></td>
                        <td style="min-width: 180px;">
                            <div>${invoice.project.customer.firstName} ${invoice.project.customer.lastName}</div>
                            <small class="text-muted"> ${invoice.project.customer.companyName}</small>
                            <small class="text-muted">${invoice.project.customer.email}</small>
                        </td>
                        <td style="min-width: 180px;">
                            <div>${invoice.project.manager.firstName} ${invoice.project.manager.lastName}</div>
                            <small class="text-muted">${invoice.project.manager.email}</small>
                        </td>
                        <td><span class="fw-semibold">$${invoice.project.totalCost}</span></td>
                        <td>${invoice.issuedDate}</td>
                        <td class="text-center">
                            <div class="project-meta">
                                <#if invoice.status == "CANCELLED">
                                    <span class="badge-soft badge-yellow">Cancelled</span>
                                <#elseif invoice.status == "PAID">
                                    <span class="badge-soft badge-green">Paid</span>
                                <#elseif invoice.status == "PENDING">
                                    <span class="badge-soft badge-blue">Pending</span>
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