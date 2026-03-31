<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Завдання проєкту | DevSync</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <style>
        body { background-color: #f8f9fa; }
        .table-container { background: #fff; border-radius: 15px; box-shadow: 0 4px 15px rgba(0,0,0,0.03); padding: 20px; }
        .text-truncate-2 { display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; }
        .dropdown-toggle-no-caret::after { display: none; }
    </style>
</head>
<body class="d-flex">

<#include "sidebar.ftl">

<div class="main-content flex-grow-1 p-4">
    <div class="container-fluid max-w-100">
        <div class="d-flex justify-content-between align-items-center mb-4 pb-2 border-bottom">
            <div>
                <h2 class="fw-bold m-0">Робочі завдання</h2>
                <p class="text-muted small mb-0 mt-1">Історія роботи над проєктами та закриті години</p>
            </div>


            <#if isAuth?? && isDeveloper?? && isDeveloper && hasActiveProject?? && hasActiveProject>
                <a href="/developer/project-assignments/create" class="btn btn-primary shadow-sm rounded-pill px-4 d-flex align-items-center gap-2">
                    <i class="bi bi-plus-lg"></i> Почати завдання
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
            <table class="table table-hover align-middle mb-0">
                <thead class="table-light text-muted small">
                <tr>
                    <th style="width: 35%;">Завдання та Проєкт</th>
                    <th style="width: 25%;">Розробник</th>
                    <th style="width: 30%;">Статус та Час</th>
                    <th class="text-end" style="width: 10%;">Дії</th>
                </tr>
                </thead>
                <tbody>
                <#if projectAssignments?? && projectAssignments?size gt 0>
                    <#list projectAssignments as assignment>
                        <tr>
                            <td>
                                <div class="fw-bold text-dark mb-1 fs-6">${assignment.title}</div>
                                <div class="text-muted small text-truncate-2 mb-2" title="${assignment.description!''}">${assignment.description!''}</div>
                                <span class="badge bg-light text-secondary border fw-normal">
                                    <i class="bi bi-folder2 me-1"></i> ${assignment.project.title}
                                </span>
                            </td>

                            <td>
                                <div class="d-flex align-items-center gap-3">
                                    <#if assignment.developer.user.imageUrl??>
                                        <img src="${assignment.developer.user.imageUrl}" class="rounded-circle object-fit-cover shadow-sm" width="45" height="45">
                                    <#else>
                                        <div class="bg-primary bg-opacity-10 text-primary rounded-circle d-flex justify-content-center align-items-center fw-bold" style="width:45px; height:45px;">
                                            ${assignment.developer.user.firstName[0]}${assignment.developer.user.lastName[0]}
                                        </div>
                                    </#if>
                                    <div class="lh-sm">
                                        <div class="fw-semibold text-dark">${assignment.developer.user.firstName} ${assignment.developer.user.lastName}</div>
                                        <small class="text-muted">${assignment.developer.user.email}</small>
                                    </div>
                                </div>
                            </td>

                            <td>
                                <div class="d-flex align-items-center gap-2 mb-2">
                                    <#if assignment.active>
                                        <span class="badge bg-primary bg-opacity-10 text-primary border border-primary-subtle rounded-pill px-2 py-1"><i class="bi bi-play-circle-fill me-1"></i> В процесі</span>
                                    <#else>
                                        <span class="badge bg-success bg-opacity-10 text-success border border-success-subtle rounded-pill px-2 py-1"><i class="bi bi-check-circle-fill me-1"></i> Завершено</span>
                                        <span class="fw-bold text-dark small"><i class="bi bi-clock-history text-muted me-1"></i> ${assignment.spentHours!0} год ${assignment.spentMinutes!0} хв</span>
                                    </#if>
                                </div>

                                <div class="small text-muted" style="font-size: 0.8rem;">
                                    <div class="mb-1">
                                        <i class="bi bi-calendar-event me-1"></i> Початок: ${assignment.formattedCreatedAt}
                                    </div>

                                    <#if !assignment.active && assignment.completedAt??>
                                        <div>
                                            <i class="bi bi-calendar-check me-1"></i> Фініш: ${assignment.formattedCompletedAt}
                                        </div>
                                    </#if>
                                </div>
                            </td>


                            <td class="text-end">
                                <#if isAuth??>
                                    <div class="dropdown">
                                        <button class="btn btn-light btn-sm rounded-circle p-2 dropdown-toggle dropdown-toggle-no-caret" type="button" data-bs-toggle="dropdown">
                                            <i class="bi bi-three-dots-vertical"></i>
                                        </button>
                                        <ul class="dropdown-menu dropdown-menu-end shadow-sm border-0 rounded-3">

                                            <#-- Дії для ДЕВА (тільки якщо таска активна) -->
                                            <#if isDeveloper?? && isDeveloper && assignment.active>
                                                <li>
                                                    <button type="button" class="dropdown-item fw-medium text-success d-flex align-items-center gap-2 py-2 w-100 text-start"
                                                            onclick="openFinishModal(${assignment.id}, '${assignment.title?js_string}')">
                                                        <i class="bi bi-check2-square"></i> Завершити завдання
                                                    </button>
                                                </li>

                                                <li>
                                                    <a class="dropdown-item d-flex align-items-center gap-2 py-2" href="/developer/project-assignments/edit/${assignment.id}">
                                                        <i class="bi bi-pencil-fill text-warning"></i> Редагувати опис
                                                    </a>
                                                </li>
                                            </#if>

                                            <#-- Дії для АДМІНА (Видалення) -->
                                            <#if isAdmin?? && isAdmin>
                                                <li><hr class="dropdown-divider opacity-50"></li>
                                                <li>
                                                    <form action="/admin/project-assignments/delete/${assignment.id}" method="post" onsubmit="return confirm('Ви впевнені, що хочете видалити це завдання? Цю дію неможливо скасувати!');">
                                                        <button type="submit" class="dropdown-item text-danger d-flex align-items-center gap-2 py-2">
                                                            <i class="bi bi-trash3-fill"></i> Видалити
                                                        </button>
                                                    </form>
                                                </li>
                                            </#if>

                                            <#-- Заглушка, якщо немає доступних дій -->
                                            <#if !isAdmin?? && (!isDeveloper?? || !assignment.active)>
                                                <li><span class="dropdown-item text-muted small">Немає доступних дій</span></li>
                                            </#if>
                                        </ul>
                                    </div>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td colspan="4" class="text-center py-5 text-muted">
                            <i class="bi bi-clipboard-x fs-1 d-block mb-3 text-secondary opacity-50"></i>
                            <span class="fw-medium fs-5">Поки що немає жодних завдань</span>
                            <p class="small mt-1">Тут з'явиться історія виконаних робіт по проєктах.</p>
                        </td>
                    </tr>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function openFinishModal(taskId, taskTitle) {
        // 1. Ставимо правильний URL в action форми
        document.getElementById('finishTaskForm').action = '/developer/project-assignments/finish/' + taskId;

        // 2. Підставляємо назву таски
        document.getElementById('finishTaskTitle').innerText = taskTitle;

        // 3. Відкриваємо модалку
        var myModal = new bootstrap.Modal(document.getElementById('finishTaskModal'));
        myModal.show();
    }
</script>

<div class="modal fade" id="finishTaskModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content border-0 shadow-lg">
            <div class="modal-header border-bottom-0 pb-0">
                <h5 class="modal-title fw-bold text-success"><i class="bi bi-check-circle-fill me-2"></i>Завершення роботи</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <form id="finishTaskForm" method="post" action="">
                <div class="modal-body">
                    <p class="text-muted small mb-4">Ви закриваєте: <strong id="finishTaskTitle" class="text-dark"></strong></p>

                    <label class="form-label fw-medium text-dark mb-2">Скільки часу витрачено?</label>
                    <div class="row g-3">
                        <div class="col-6">
                            <div class="input-group">
                                <input type="number" name="spentHours" class="form-control" min="0" max="999" value="0" required>
                                <span class="input-group-text bg-light">год</span>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="input-group">
                                <input type="number" name="spentMinutes" class="form-control" min="0" max="59" value="0" required>
                                <span class="input-group-text bg-light">хв</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer border-top-0 pt-0">
                    <button type="button" class="btn btn-light" data-bs-dismiss="modal">Скасувати</button>
                    <button type="submit" class="btn btn-success px-4 shadow-sm">Підтвердити</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>