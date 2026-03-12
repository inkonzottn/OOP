<#import "client/templ-client.ftl" as p>

<@p.pages>
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
        <div class="card shadow-lg p-4" style="width: 100%; max-width: 500px; border-radius: 15px;">
            <div class="text-center mb-4">
                <h2 class="fw-bold">Registration</h2>
                <p class="text-muted">Create your profile</p>
            </div>

            <#if message??>
                <div class="alert alert-danger text-center">${message}</div>
            </#if>

            <form method="post" action="/registration">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">First name</label>
                        <input type="text" name="firstName"
                               class="form-control ${(errors.firstName??)?string('is-invalid', '')}"
                               value="${(user.firstName)!''}" placeholder="Mike">
                        <#if errors.firstName??>
                            <div class="invalid-feedback">${errors.firstName}</div>
                        </#if>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Last name</label>
                        <input type="text" name="lastName"
                               class="form-control ${(errors.lastName??)?string('is-invalid', '')}"
                               value="${(user.lastName)!''}" placeholder="Wazowski">
                        <#if errors.lastName??>
                            <div class="invalid-feedback">${errors.lastName}</div>
                        </#if>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="text" name="email"
                           class="form-control ${(errors.email??)?string('is-invalid', '')}"
                           value="${(user.email)!''}" placeholder="example@dev.com">
                    <#if errors.email??>
                        <div class="invalid-feedback">${errors.email}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" name="password"
                           class="form-control ${(errors.password??)?string('is-invalid', '')}"
                           placeholder="••••••••">
                    <#if errors.password??>
                        <div class="invalid-feedback">${errors.password}</div>
                    </#if>
                </div>

                <button type="submit" class="btn btn-success w-100 py-2 shadow-sm">
                    Create account
                </button>

                <div class="text-center mt-3">
                    <span class="text-muted small">Already have a profile?</span><br>
                    <a href="/login" class="text-decoration-none fw-bold">Log in</a>
                </div>
            </form>
        </div>
    </div>
</@p.pages><#import "client/templ-client.ftl" as p>