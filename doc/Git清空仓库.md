1.切换到新的分支

git checkout --orphan latest_branch
　　
2.缓存所有文件（除了.gitignore中声明排除的）

 git add -A
　　
3.提交跟踪过的文件（Commit the changes）

 git commit -am "commit message"
　　4.删除master分支（Delete the branch）

git branch -D master
git branch -D main
　　5.重命名当前分支为master（Rename the current branch to master）

 git branch -m master
  git branch -m main
　　6.提交到远程master分支 （Finally, force update your repository）

 git push -f origin master
  git push -f origin main
　　通过以上几步就可以简单地把一个Git仓库的历史提交记录清除掉了