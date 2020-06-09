# 基于openjdk 镜像
FROM openjdk:8u111

# 将本地文件夹挂在到当前容器
VOLUME /tmp

# 复制文件到容器
ADD *.jar app.jar

# 声明需要暴露的端口
EXPOSE 8080
ADD *.jar app.jar


ENTRYPOINT ["java","-jar","/app.jar"]

# Ubuntu 时区
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
# 配置容器启动后执行的命令
#RUN bash -c "touch /app.jar"
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
#  && echo 'Asia/Shanghai' >/etc/timezone \