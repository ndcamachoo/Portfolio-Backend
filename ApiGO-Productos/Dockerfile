FROM golang:1.16.9-alpine

WORKDIR /go/src/myapp

COPY ./ ./

RUN apk add --no-cache gcc musl-dev
RUN go mod download

ENTRYPOINT ["go", "run", "main.go"]
EXPOSE 9098
