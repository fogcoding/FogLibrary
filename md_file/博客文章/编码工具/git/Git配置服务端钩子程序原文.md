# Server hooks[CORE](https://about.gitlab.com/pricing/)

[Introduced](https://gitlab.com/gitlab-org/gitlab/-/issues/196051) in GitLab 12.8 replacing Custom Hooks.

Git supports hooks that are executed on different actions. These hooks run on the server and can be used to enforce specific commit policies or perform other tasks based on the state of the repository.

Git supports the following hooks:

- `pre-receive`
- `post-receive`
- `update`

See [the Git documentation](https://git-scm.com/book/en/v2/Customizing-Git-Git-Hooks#_server_side_hooks) for more information about each hook type.

Server-side Git hooks can be configured for:

- [A single repository](https://docs.gitlab.com/ee/administration/server_hooks.html#create-a-server-hook-for-a-repository).
- [All repositories](https://docs.gitlab.com/ee/administration/server_hooks.html#create-a-global-server-hook-for-all-repositories).

Note the following about server hooks:

- Server hooks must be configured on the file system of the GitLab server. Only GitLab server administrators are able to complete these tasks. If you don’t have file system access, see possible alternatives such as:
  - [Webhooks](https://docs.gitlab.com/ee/user/project/integrations/webhooks.html).
  - [GitLab CI/CD](https://docs.gitlab.com/ee/ci/README.html).
  - [Push Rules](https://docs.gitlab.com/ee/push_rules/push_rules.html), for a user-configurable Git hook interface. 
- Server hooks aren’t replicated to [Geo](https://docs.gitlab.com/ee/administration/geo/replication/index.html) secondary nodes.

## Create a server hook for a repository

If you are not using [hashed storage](https://docs.gitlab.com/ee/administration/repository_storage_types.html#hashed-storage), the project’s repository directory might not exactly match the instructions below. In that case:

- For an installation from source, the path is usually `/home/git/repositories/<group>/<project>.git`.
- For Omnibus GitLab installs, the path is usually `/var/opt/gitlab/git-data/repositories/<group>/<project>.git`.

Follow the steps below to set up a server-side hook for a repository:

1. Navigate to **Admin area > Projects** and click on the project you want to add a server hook to.
2. Locate the **Gitaly relative path** on the page that appears. This is where the server hook must be implemented. For information on interpreting the relative path, see [Translating hashed storage paths](https://docs.gitlab.com/ee/administration/repository_storage_types.html#translating-hashed-storage-paths).
3. On the file system, create a new directory in this location called `custom_hooks`.
4. Inside the new `custom_hooks` directory, create a file with a name matching the hook type. For example, for a pre-receive hook the filename should be `pre-receive` with no extension.
5. Make the hook file executable and ensure that it’s owned by the Git user.
6. Write the code to make the server hook function as expected. Hooks can be in any language. Ensure the [“shebang”](https://en.wikipedia.org/wiki/Shebang_(Unix)) at the top properly reflects the language type. For example, if the script is in Ruby the shebang is probably `#!/usr/bin/env ruby`.

Assuming the hook code is properly implemented, the hook code is executed as appropriate.

## Create a global server hook for all repositories

To create a Git hook that applies to all of the repositories in your instance, set a global server hook. The default global server hook directory is in the GitLab Shell directory. Any hook added there applies to all repositories.

The default directory:

- For an installation from source is usually `/home/git/gitlab-shell/hooks`.
- For Omnibus GitLab installs is usually `/opt/gitlab/embedded/service/gitlab-shell/hooks`.

To use a different directory for global server hooks, set `custom_hooks_dir` in Gitaly configuration:

- For Omnibus installations, this is set in `gitlab.rb`.
- For source installations, the configuration location depends on the GitLab version. For:
  - GitLab 13.0 and earlier, this is set in `gitlab-shell/config.yml`.
  - GitLab 13.1 and later, this is set in `gitaly/config.toml` under the `[hooks]` section.

**Note:** The `custom_hooks_dir` value in `gitlab-shell/config.yml` is still honored in GitLab 13.1 and later if the value in `gitaly/config.toml` is blank or non-existent.

Follow the steps below to set up a global server hook for all repositories:

1. On the GitLab server, navigate to the configured global server hook directory.
2. Create a new directory in this location. Depending on the type of hook, it can be either a `pre-receive.d`, `post-receive.d`, or `update.d` directory.
3. Inside this new directory, add your hook. Hooks can be in any language. Ensure the [“shebang”](https://en.wikipedia.org/wiki/Shebang_(Unix)) at the top properly reflects the language type. For example, if the script is in Ruby the shebang is probably `#!/usr/bin/env ruby`.
4. Make the hook file executable and ensure that it’s owned by the Git user.

Now test the hook to check whether it is functioning properly.

## Chained hooks

[Introduced](https://gitlab.com/gitlab-org/gitlab-shell/-/merge_requests/93) in GitLab Shell 4.1.0 and GitLab 8.15.

Server hooks set [per project](https://docs.gitlab.com/ee/administration/server_hooks.html#create-a-server-hook-for-a-repository) or [globally](https://docs.gitlab.com/ee/administration/server_hooks.html#create-a-global-server-hook-for-all-repositories) can be executed in a chain.

Server hooks are searched for and executed in the following order of priority:

- Built-in GitLab server hooks. These are not user-customizable.
- `<project>.git/custom_hooks/<hook_name>`: Per-project hooks. This was kept for backwards compatibility.
- `<project>.git/custom_hooks/<hook_name>.d/*`: Location for per-project hooks.
- `<custom_hooks_dir>/<hook_name>.d/*`: Location for all executable global hook files except editor backup files.

Within a directory, server hooks:

- Are executed in alphabetical order.
- Stop executing when a hook exits with a non-zero value.

Note:

- `<hook_name>.d` must be either `pre-receive.d`, `post-receive.d`, or `update.d` to work properly. Any other names are ignored.
- Files in `.d` directories must be executable and not match the backup file pattern (`*~`).
- For `<project>.git` you need to [translate](https://docs.gitlab.com/ee/administration/repository_storage_types.html#translating-hashed-storage-paths) your project name into the hashed storage format that GitLab uses.

## Environment Variables

The following set of environment variables are available to server hooks.

| Environment variable | Description                                                  |
| :------------------- | :----------------------------------------------------------- |
| `GL_ID`              | GitLab identifier of user that initiated the push. For example, `user-2234` |
| `GL_PROJECT_PATH`    | (GitLab 13.2 and later) GitLab project path                  |
| `GL_PROTOCOL`        | (GitLab 13.2 and later) Protocol used with push              |
| `GL_REPOSITORY`      | `project-<id>` where `id` is the ID of the project           |
| `GL_USERNAME`        | GitLab username of the user that initiated the push          |

Pre-receive and post-receive server hooks can also access the following Git environment variables.

| Environment variable               | Description                                                  |
| :--------------------------------- | :----------------------------------------------------------- |
| `GIT_ALTERNATE_OBJECT_DIRECTORIES` | Alternate object directories in the quarantine environment. See [Git `receive-pack` documentation](https://git-scm.com/docs/git-receive-pack#_quarantine_environment). |
| `GIT_OBJECT_DIRECTORY`             | GitLab project path in the quarantine environment. See [Git `receive-pack` documentation](https://git-scm.com/docs/git-receive-pack#_quarantine_environment). |
| `GIT_PUSH_OPTION_COUNT`            | Number of push options. See [Git `pre-receive` documentation](https://git-scm.com/docs/githooks#pre-receive). |
| `GIT_PUSH_OPTION_<i>`              | Value of push options where `i` is from `0` to `GIT_PUSH_OPTION_COUNT - 1`. See [Git `pre-receive` documentation](https://git-scm.com/docs/githooks#pre-receive). |

**Note:** While other environment variables can be passed to server hooks, your application should not rely on them as they can change.

## Transition to Go

Introduced in GitLab 13.2 using feature flags.

The following server hooks have been re-implemented in Go:

- `pre-receive`, with the Go implementation used by default. To use the Ruby implementation instead, [disable](https://docs.gitlab.com/ee/administration/feature_flags.html#enable-or-disable-the-feature) the `:gitaly_go_preceive_hook` feature flag.
- `update`, with the Go implementation used by default. To use the Ruby implementation instead, [disable](https://docs.gitlab.com/ee/administration/feature_flags.html#enable-or-disable-the-feature) the `:gitaly_go_update_hook` feature flag.
- `post-receive`, however the Ruby implementation is used by default. To use the Go implementation instead, [enable](https://docs.gitlab.com/ee/administration/feature_flags.html#enable-or-disable-the-feature) the `:gitaly_go_postreceive_hook` feature flag.

## Custom error messages

[Introduced](https://gitlab.com/gitlab-org/gitlab-foss/-/merge_requests/5073) in GitLab 8.10.

To have custom error messages appear in GitLab’s UI when a commit is declined or an error occurs during the Git hook, your script should:

- Send the custom error messages to either the script’s `stdout` or `stderr`.
- Prefix each message with `GL-HOOK-ERR:` with no characters appearing before the prefix.

### Example custom error message

This hook script written in Bash generates the following message in GitLab’s UI:

```
#!/bin/sh
echo "GL-HOOK-ERR: My custom error message.";
exit 1
```

[![Custom message from custom Git hook](https://docs.gitlab.com/ee/administration/img/custom_hooks_error_msg.png)](https://docs.gitlab.com/ee/administration/img/custom_hooks_error_msg.png)