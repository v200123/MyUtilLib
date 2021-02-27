package  com.lastcoffee

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskContainer


class TestCopy implements Plugin<Project>{

    @Override
    void apply(Project target) {
        def meaasge = target.extensions.create("test", TestMeaasge)
        target.task("CopyFile"){

            target.allprojects.forEach{project->
                println "当前Project的名字是:${project.name}"
                println "当前Project的Task中有:${project.tasks}"
                if(project.name == "app")
                {
                    project.afterEvaluate {
                        project.tasks.each {task ->
                            if(task.toString().contains("assemble"))
                            {
                                task.doLast {
                                    println "rootDir的路径是:${target.rootDir}"
                                    println "projectDir的路径是:${target.projectDir}"
                                    println "buildDir的路径是:${target.buildDir}"
                                    def typeName = task.toString().split("assemble")[1]
                                    def simpleName = "app-${typeName}.apk"
                                    def file = new  File("D://","apk")
                                    if(!file.exists())
                                    {
                                        file.mkdirs()
                                    }
                                try {
                                    target.copy {
                                        from target.buildDir.getAbsolutePath()+"/outputs/apk/"
                                                into "D:/apk/"
                                    }
                                }catch(Exception e){
                                    println "失败，原因为：${e}"

                                }
                                        new File(target.buildDir,"outputs//apk").deleteDir()
                                        println "复制成功"
                                }
                            }

                        }


                    }
                }
            }


        }
    }

}



class TestMeaasge{
    String msg = "aaaaaa"
}