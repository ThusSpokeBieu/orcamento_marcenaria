package notification

type HttpError struct {
	StatusCode int      `json:"status_code"`
	Response   string   `json:"response"`
	Message    string   `json:"message"`
	Errors     []string `json:"errors"`
}

func NewBadRequest(message string, errors *[]error) HttpError {
	errorsMsgs := make([]string, 0, len(*errors))

	for _, err := range *errors {
		errorsMsgs = append(errorsMsgs, err.Error())
	}

	return HttpError{
		StatusCode: 400,
		Response:   "Bad Request",
		Message:    message,
		Errors:     errorsMsgs,
	}
}
